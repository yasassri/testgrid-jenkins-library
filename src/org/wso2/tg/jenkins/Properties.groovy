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

import org.wso2.tg.jenkins.util.Common

@Singleton
class Properties {

    static def TESTGRID_NAME            = "WSO2-TestGrid"
    static def TESTGRID_DIST_LOCATION   = "/testgrid/testgrid-home/testgrid-dist/"
    static def TESTGRID_HOME            = "/testgrid/testgrid-home/"
    static def PRODUCT                  = ""
    static def TESTGRID_YAML_LOCATION   = ""
    static def AWS_ACCESS_KEY_ID        = ""
    static def AWS_SECRET_ACCESS_KEY    = ""
    static def TOMCAT_USERNAME          = ""
    static def TOMCAT_PASSWORD          = ""
    static def WUM_UAT_URL              = ""
    static def WUM_UAT_APP_KEY          = ""
    static def USER_NAME                = ""
    static def PASSWORD                 = ""
    static def GIT_WUM_USERNAME         = ""
    static def GIT_WUM_PASSWORD         = ""
    static def CURRENT_WORKSPACE        = ""
    static def JOB_CONFIG_YAML          = "job-config.yaml"
    static def JOB_CONFIG_YAML_PATH     = ""
    static def PRODUCT_GIT_URL          = ""
    static def PRODUCT_GIT_BRANCH       = ""
    static def PRODUCT_DIST_DOWNLOAD_API= ""
    static def WUM_CHANNEL              = ""
    static def PRODUCT_CODE             = ""
    static def WUM_PRODUCT_VERSION      = ""
    static def USE_CUSTOM_TESTNG        = ""
    static def EXECUTOR_COUNT           = ""


    def initProperties(def  propertyMap){
        Common util = new Common()

        PRODUCT = propertyMap.get(Constants.PRODUCT)
        TESTGRID_YAML_LOCATION = propertyMap.get(Constants.INFRA_LOCATION) + "/jobs/" + propertyMap.get("JOB_BASE_NAME") + "/testgrid" + ".yaml"
        CURRENT_WORKSPACE = util.getCurrentWorkspace().toString()
        JOB_CONFIG_YAML_PATH = util.getCurrentWorkspace().toString() + "/" + JOB_CONFIG_YAML
        PRODUCT_GIT_URL = propertyMap.get(Constants.PRODUCT_GIT_URL)
        PRODUCT_GIT_BRANCH = propertyMap.get(Constants.PRODUCT_GIT_BRANCH)
        PRODUCT_DIST_DOWNLOAD_API = propertyMap.get(Constants.PRODUCT_DIST_DOWNLOAD_API)
        WUM_CHANNEL = propertyMap.get(Constants.WUM_CHANNEL)
        PRODUCT_CODE = propertyMap.get(Constants.PRODUCT_CODE)
        WUM_PRODUCT_VERSION = propertyMap.get(Constants.WUM_PRODUCT_VERSION)
        USE_CUSTOM_TESTNG = propertyMap.get(Constants.USE_CUSTOM_TESTNG)
        EXECUTOR_COUNT = propertyMap.get(Constants.EXECUTOR_COUNT)
        AWS_ACCESS_KEY_ID        = util.getCredentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY    = util.getCredentials('AWS_SECRET_ACCESS_KEY')
        TOMCAT_USERNAME          = util.getCredentials('TOMCAT_USERNAME')
        TOMCAT_PASSWORD          = util.getCredentials('TOMCAT_PASSWORD')
        WUM_UAT_URL              = util.getCredentials('WUM_UAT_URL')
        WUM_UAT_APP_KEY          = util.getCredentials('WUM_UAT_APPKEY')
        USER_NAME                = util.getCredentials('WUM_USERNAME')
        PASSWORD                 = util.getCredentials('WUM_PASSWORD')
        GIT_WUM_USERNAME         = util.getCredentials('GIT_WUM_USERNAME')
        GIT_WUM_PASSWORD         = util.getCredentials('GIT_WUM_PASSWORD')
    }
}

