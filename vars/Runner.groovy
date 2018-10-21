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


import org.wso2.tg.jenkins.PipelineContext
import org.wso2.tg.jenkins.alert.Slack
import org.wso2.tg.jenkins.alert.Email
import org.wso2.tg.jenkins.executors.TestGridExecutor
import org.wso2.tg.jenkins.util.AWSUtils
import org.wso2.tg.jenkins.executors.TestExecutor
import org.wso2.tg.jenkins.Properties
import org.wso2.tg.jenkins.util.RuntimeUtils
import org.wso2.tg.jenkins.util.WorkSpaceUtils


// The pipeline should reside in a call block
def call(def ab) {
    // Setting the current pipeline context
    PipelineContext.instance.setContext(this)
    // Initializing environment properties
    def props = Properties.instance
    props.instance.initProperties(ab.getRawBuild().getEnvironment())

    // For scaling we need to create slave nodes before starting the pipeline and schedule it appropriately
    def alert = new Slack()
    def email = new Email()
    def awsHelper = new AWSUtils()
    def testExecutor = new TestExecutor()
    def tgExecutor = new TestGridExecutor()
    def runtime = new RuntimeUtils()
    def ws = new WorkSpaceUtils()

    pipeline {
        agent {
            node {
                label ""
                customWorkspace "${props.WORKSPACE}"
            }
        }
        tools {
            jdk 'jdk8'
        }

        stages {
            stage('Preparation') {
                steps {
                    script {
                        try {
                            //alert.sendNotification('STARTED', "Initiation", "#build_status_verbose")
                            ///alert.sendNotification('STARTED', "Initiation", "#build_status")
                            echo pwd()
                            deleteDir()
                            echo "111"
                            pwd()
                            // Increasing the TG JVM memory params
                            runtime.increaseTestGridRuntimeMemory("2G", "2G")
                            // Get testgrid.yaml from jenkins managed files
                            configFileProvider(
                                    [configFile(fileId: "${props.PRODUCT}-testgrid-yaml", targetLocation:
                                            "${props.TESTGRID_YAML_LOCATION}")]) {
                            }

                            //Constructing the product git url if test mode is wum. Adding the Git username and password into the product git url.
                            if ("${props.TEST_MODE}" == "WUM") {
                                def url = "${props.PRODUCT_GIT_URL}"
                                def values = url.split('//g')
                                def productGitUrl =
                                        "${values[0]}//${props.GIT_WUM_USERNAME}:${props.GIT_WUM_PASSWORD}@g${values[1]}"
                                PRODUCT_GIT_URL = "${productGitUrl}"

                            } else {
                                PRODUCT_GIT_URL = "${props.PRODUCT_GIT_URL}"
                            }
                            echo "Creating Job config!!!!"
                            // Creating the job config file
                            ws.createJobConfigYamlFile("${props.JOB_CONFIG_YAML_PATH}")
                            echo "Done Creating Job config!!!!"
                            sh """
				                    echo The job-config.yaml :
                                    cat ${props.JOB_CONFIG_YAML_PATH}
                                    """

                            echo "Generating test plans!!"
                            tgExecutor.generateTesPlans(props.PRODUCT, props.JOB_CONFIG_YAML_PATH)

                            echo "Stashing testplans to be used in different slave nodes"
                            dir("${props.WORKSPACE}") {
                                stash name: "test-plans", includes: "test-plans/**"
                            }
                        } catch (e) {
                            currentBuild.result = "FAILED"
                        } finally {
                            //alert.sendNotification(currentBuild.result, "preparation", "#build_status_verbose")
                        }
                    }
                }
            }

            stage('parallel-run') {
                steps {
                    script {
                        def name = "unknown"
                        try {
                            parallel_executor_count = 12
                            if (props.EXECUTOR_COUNT != "null") {
                                echo "executor count is" + props.EXECUTOR_COUNT
                                parallel_executor_count = props.EXECUTOR_COUNT
                            }
                            def tests = testExecutor.getTestExecutionMap(parallel_executor_count)
                            parallel tests
                        } catch (e) {
                            currentBuild.result = "FAILED"
                            //alert.sendNotification(currentBuild.result, "Parallel", "#build_status_verbose")
                        }
                    }
                }
            }

//                post {
//                    always {
//                        script {
//                            try {
//                                sh """
//                                cd ${TESTGRID_HOME}/testgrid-dist/${TESTGRID_NAME}
//                                ./testgrid finalize-run-testplan \
//                                --product ${PRODUCT} --workspace ${PWD}
//                            """
//
//                                sh """
//                                export DISPLAY=:95.0
//                                cd ${TESTGRID_HOME}/testgrid-dist/${TESTGRID_NAME}
//                                ./testgrid generate-email \
//                                --product ${PRODUCT} \
//                                --workspace ${PWD}
//                            """
//                                awsHelper.uploadCharts()
//                                //Send email for failed results.
//                                if (fileExists("${PWD}/SummarizedEmailReport.html")) {
//                                    def emailBody = readFile "${PWD}/SummarizedEmailReport.html"
//                                    email.send("'${env.JOB_NAME}' Integration Test Results! #(${env.BUILD_NUMBER})", "${emailBody}")
//                                } else {
//                                    echo "No SummarizedEmailReport.html file found!!"
//                                    email.send("'${env.JOB_NAME}'#(${env.BUILD_NUMBER}) - SummarizedEmailReport.html " +
//                                            "file not found", "Could not find the summarized email report ${env.BUILD_URL}. This is an error in " +
//                                            "testgrid.")
//                                }
//                            } catch (e) {
//                                currentBuild.result = "FAILED"
//                            } finally {
//                                alert.sendNotification(currentBuild.result, "completed", "#build_status")
//                                alert.sendNotification(currentBuild.result, "completed", "#build_status_verbose")
//                            }
//                        }
//                    }
//                }
        }
    }
}