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

package org.wso2.tg.jenkins.util

/**
 * Create the given directory.
 *
 * @param filePath full qualified path Of directory
 * @throws IOException if file creation fails
 */
def createJobConfigYamlFile(filePath) throws IOException{


    // https://jenkins.io/doc/pipeline/steps/pipeline-utility-steps/#writeyaml-write-a-yaml-from-an-object
    File file = new File (filePath)
    file.append()
    file.createNewFile()
    ssh """
    echo 'keyFileLocation: workspace/testgrid-key.pem' > ${filePath}
    echo 'infrastructureRepository: ${INFRA_LOCATION}/' >> ${filePath}
    echo 'deploymentRepository: ${INFRA_LOCATION}/' >> ${filePath}
    echo 'scenarioTestsRepository: ${SCENARIOS_LOCATION}' >> ${filePath}
    echo 'testgridYamlLocation: ${TESTGRID_YAML_LOCATION}' >> ${filePath}
    echo 'properties:' >> ${filePath}
    echo '  PRODUCT_GIT_URL: ${PRODUCT_GIT_URL}' >> ${filePath}
    echo '  PRODUCT_GIT_BRANCH: ${PRODUCT_GIT_BRANCH}' >> ${filePath}
    echo '  PRODUCT_DIST_DOWNLOAD_API: ${PRODUCT_DIST_DOWNLOAD_API}' >> ${filePath}
    echo '  SQL_DRIVERS_LOCATION_UNIX: ${SQL_DRIVERS_LOCATION_UNIX}' >> ${filePath}
    echo '  SQL_DRIVERS_LOCATION_WINDOWS: ${SQL_DRIVERS_LOCATION_WINDOWS}' >> ${filePath}
    echo '  REMOTE_WORKSPACE_DIR_UNIX: ${REMOTE_WORKSPACE_DIR_UNIX}' >> ${filePath}
    echo '  REMOTE_WORKSPACE_DIR_WINDOWS: ${REMOTE_WORKSPACE_DIR_WINDOWS}' >> ${filePath}
    echo '  gitURL: ${PRODUCT_GIT_URL}' >> ${filePath}
    echo '  gitBranch: ${PRODUCT_GIT_BRANCH}' >> ${filePath}
    echo '  productDistDownloadApi: ${PRODUCT_DIST_DOWNLOAD_API}' >> ${filePath}
    echo '  sqlDriversLocationUnix: ${SQL_DRIVERS_LOCATION_UNIX}' >> ${filePath}
    echo '  sqlDriversLocationWindows: ${SQL_DRIVERS_LOCATION_WINDOWS}' >> ${filePath}
    echo '  RemoteWorkspaceDirPosix: ${REMOTE_WORKSPACE_DIR_UNIX}' >> ${filePath}
    echo '  LATEST_PRODUCT_RELEASE_API: ${LATEST_PRODUCT_RELEASE_API}' >> ${filePath}
    echo '  LATEST_PRODUCT_BUILD_ARTIFACTS_API: ${LATEST_PRODUCT_BUILD_ARTIFACTS_API}' >> ${filePath}
    echo '  TEST_MODE: ${TEST_MODE}' >> ${filePath}
    echo '  runOnBranch: "false"' >> ${filePath}
    echo '  WUM_CHANNEL: "${WUM_CHANNEL}"' >> ${filePath}
    echo '  PRODUCT_CODE: "${PRODUCT_CODE}"' >> ${filePath}
    echo '  WUM_PRODUCT_VERSION: "${WUM_PRODUCT_VERSION}"' >> ${filePath}
    echo '  USE_CUSTOM_TESTNG: "${USE_CUSTOM_TESTNG}"' >> ${filePath}
    """
}