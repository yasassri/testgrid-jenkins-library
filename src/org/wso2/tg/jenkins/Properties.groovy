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
package org.wso2.tg.jenkins

@Singleton
class Properties implements Serializable {

//    def props = [
//            (Constants.TESTGRID_NAME)            : "WSO2-TestGrid",
//            (Constants.TESTGRID_DIST_LOCATION)   : "/testgrid/testgrid-home/testgrid-dist/",
//            (Constants.TESTGRID_HOME)            : "/testgrid/testgrid-home/",
//            (Constants.PRODUCT)                  : "${JOB_BASE_NAME}",
//            (Constants.TESTGRID_YAML_LOCATION)   : "${INFRA_LOCATION}/jobs/${PRODUCT}/testgrid.yaml",
//            (Constants.AWS_ACCESS_KEY_ID)        : credentials('AWS_ACCESS_KEY_ID'),
//            (Constants.AWS_SECRET_ACCESS_KEY)    : credentials('AWS_SECRET_ACCESS_KEY'),
//            (Constants.TOMCAT_USERNAME)          : credentials('TOMCAT_USERNAME'),
//            (Constants.TOMCAT_PASSWORD)          : credentials('TOMCAT_PASSWORD'),
//            (Constants.WUM_UAT_URL)              : credentials('WUM_UAT_URL'),
//            (Constants.WUM_UAT_APP_KEY)          : credentials('WUM_UAT_APPKEY'),
//            (Constants.USER_NAME)                : credentials('WUM_USERNAME'),
//            (Constants.PASSWORD)                 : credentials('WUM_PASSWORD'),
//            (Constants.GIT_WUM_USERNAME)         : credentials('GIT_WUM_USERNAME'),
//            (Constants.GIT_WUM_PASSWORD)         : credentials('GIT_WUM_PASSWORD'),
//            (Constants.CURRENT_WORKSPACE)        : pwd(),
//            (Constants.JOB_CONFIG_YAML)          : "job-config.yaml",
//            (Constants.JOB_CONFIG_YAML_PATH)     : "${PWD}/${JOB_CONFIG_YAML}",
//            (Constants.PRODUCT_GIT_URL)          : "${PRODUCT_GIT_URL}",
//            (Constants.PRODUCT_GIT_BRANCH)       : "${PRODUCT_GIT_BRANCH}",
//            (Constants.PRODUCT_DIST_DOWNLOAD_API): "${PRODUCT_DIST_DOWNLOAD_API}",
//            (Constants.WUM_CHANNEL)              : "${WUM_CHANNEL}",
//            (Constants.PRODUCT_CODE)             : "${PRODUCT_CODE}",
//            (Constants.WUM_PRODUCT_VERSION)      : "${WUM_PRODUCT_VERSION}",
//            (Constants.USE_CUSTOM_TESTNG)        : "${USE_CUSTOM_TESTNG}",
//            (Constants.EXECUTOR_COUNT)           : "${EXECUTOR_COUNT}"
//    ]

    def props2 = [ "A" : "B"]

    // Returns a requested property.
    def getProperty(property) {
        return props2.get(property)
    }
}
