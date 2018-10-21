/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.tg.jenkins.executors

import org.wso2.tg.jenkins.Properties
import org.wso2.tg.jenkins.util.Common
import org.wso2.tg.jenkins.util.AWSUtils
import org.wso2.tg.jenkins.alert.Slack
import org.wso2.tg.jenkins.util.FileUtils

def runPlan(tPlan, testPlanId) {
    def commonUtil = new Common()
    def notfier = new Slack()
    def awsHelper = new AWSUtils()
    def fileUtil = new FileUtils()
    def props = Properties.instance

    fileUtil.createDirectory("${props.WORKSPACE}/${testPlanId}")
    echo "Preparing workspace"
    prepareWorkspace(tPlan, testPlanId)
    //sleep(time:commonUtil.getRandomNumber(10),unit:"SECONDS")
    echo "Unstashing test-plans and testgrid.yaml to ${props.WORKSPACE}/${testPlanId}"
    dir("${props.WORKSPACE}/${testPlanId}") {
        unstash name: "test-plans"
    }

    def name = commonUtil.getParameters("${props.WORKSPACE}/${testPlanId}/${tPlan}")
    notfier.sendNotification("STARTED", "parallel \n Infra : " + name, "#build_status_verbose")
    try {
        sh """
            echo Running Test-Plan: ${tPlan}
            java -version
            #Need to change directory to root to run the next command properly
            cd /
            #.${props.TESTGRID_HOME}/testgrid-dist/${props.TESTGRID_NAME}/testgrid run-testplan --product ${props.PRODUCT} \
            #--file ${props.WORKSPACE}/${testPlanId}/${tPlan} --workspace ${props.WORKSPACE}/${testPlanId}        
        """
        commonUtil.truncateTestRunLog(testPlanId)
    } catch (Exception err) {
        echo "Error : ${err}"
        currentBuild.result = 'UNSTABLE'
    } finally {
        notfier.sendNotification(currentBuild.result, "Parallel \n Infra : " + name, "#build_status_verbose")
    }

    echo "RESULT: ${currentBuild.result}"

        awsHelper.uploadToS3(testPlanId)
}

//@NonCPS
def getTestExecutionMap(parallel_executor_count) {
    def commonUtils = new Common()
    def props = Properties.instance
    def parallelExecCount = parallel_executor_count as int
    def name = "unknown"
    def tests = [:]
    echo pwd()
    sh """
    ls -al
    echo "XXXX Plans "
    ls test-plans
    """
    def files = findFiles(glob: '**/test-plans/*.yaml')
    echo "Parallel exec count "+ parallelExecCount
    echo "Parallel exec count files length " + files.length
    for (int f = 1; f < parallelExecCount + 1 && f <= files.length; f++) {
        def executor = f
        echo "11111"
        name = commonUtils.getParameters("${props.WORKSPACE}/test-plans/" + files[f - 1].name)
        echo "2222"
        echo name
        tests["${name}"] = {
            node {
                stage("Parallel Executor : ${executor}") {
                    script {
                        int processFileCount = 0
                        if (files.length < parallelExecCount) {
                            processFileCount = 1
                        } else {
                            processFileCount = files.length / parallelExecCount
                        }
                        if (executor == parallelExecCount) {
                            for (int i = processFileCount * (executor - 1); i < files.length; i++) {
                                /*IMPORTANT: Instead of using 'i' directly in your logic below, 
                                you should assign it to a new variable and use it. (To avoid same 'i-object' being refered)*/
                                // Execution logic
                                int fileNo = i
                                testplanId = commonUtils.getTestPlanId("${props.WORKSPACE}/test-plans/" + files[fileNo].name)
                                runPlan(files[i], testplanId)
                            }
                        } else {
                            for (int i = 0; i < processFileCount; i++) {
                                int fileNo = processFileCount * (executor - 1) + i
                                testplanId = commonUtils.getTestPlanId("${props.WORKSPACE}/test-plans/" + files[fileNo].name)
                                runPlan(files[fileNo], testplanId)
                            }
                        }
                    }
                }
            }
        }
    }
    return tests
}

//@NonCPS
def prepareWorkspace(tPlan, testPlanId){
    def props = Properties.instance

    sh """
        echo Executing Test Plan : ${tPlan} On directory : ${testPlanId}
        echo Creating workspace and builds sub-directories
        rm -r -f ${props.WORKSPACE}/${testPlanId}/
        mkdir -p ${props.WORKSPACE}/${testPlanId}
        mkdir -p ${props.WORKSPACE}/${testPlanId}/builds
        mkdir -p ${props.WORKSPACE}/${testPlanId}/workspace
        #Cloning should be done before unstashing TestGridYaml since its going to be injected
        #inside the cloned repository
        echo Cloning ${props.SCENARIOS_REPOSITORY} into ${props.WORKSPACE}/${testPlanId}/${props.SCENARIOS_LOCATION}
        cd ${props.WORKSPACE}/${testPlanId}/workspace
        git clone ${props.SCENARIOS_REPOSITORY}

        echo Cloning ${props.INFRASTRUCTURE_REPOSITORY} into ${props.WORKSPACE}/${testPlanId}/${props.INFRA_LOCATION}
        git clone ${props.INFRASTRUCTURE_REPOSITORY}
        cp /testgrid/testgrid-prod-key.pem ${props.WORKSPACE}/${testPlanId}/workspace/testgrid-key.pem
        chmod 400 ${props.WORKSPACE}/${testPlanId}/workspace/testgrid-key.pem
        echo Workspace directory content:
        ls ${props.WORKSPACE}/${testPlanId}/
        #echo Test-plans directory content:
        #ls ${props.WORKSPACE}/${testPlanId}/test-plans/
    """
}
