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

import hudson.slaves.EnvironmentVariablesNodeProperty
import hudson.slaves.NodeProperty
import hudson.slaves.NodePropertyDescriptor
import hudson.util.DescribableList
import jenkins.model.Jenkins
import hudson.EnvVars
import hudson.model.Environment

class Properties {

    def instance = Jenkins.getInstance()
    //def env  = EnvVars.get("")
    def jenkinsHome = Jenkins.instance.globalNodeProperties
    DescribableList<NodeProperty<?>, NodePropertyDescriptor> globalNodeProperties = instance.getNodeProperties()
   // List<EnvironmentVariablesNodeProperty> envVarsNodePropertyList = globalNodeProperties.getAll
   //(EnvironmentVariablesNodeProperty.class)
    def nodes = Jenkins.instance.globalNodeProperties

    def b = nodes.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)

    def a = "{JOB_BASE_NAME}"

    def getP() {
        return instance.getNodes().size().toString()
    }

    def getQ() {
        return globalNodeProperties.toList().size().toString()
    }
}
//class Properties {

//    static final def TESTGRID_NAME            = "WSO2-TestGrid"
//    static final def TESTGRID_DIST_LOCATION   = "/testgrid/testgrid-home/testgrid-dist/"
//    static final def TESTGRID_HOME            = "/testgrid/testgrid-home/"
    //static final def PRODUCT                  = "${JOB_BASE_NAME}"
//    static final def TESTGRID_YAML_LOCATION   = "${INFRA_LOCATION}/jobs/${JOB_BASE_NAME}/testgrid.yaml"
//    static final def AWS_ACCESS_KEY_ID        = credentials('AWS_ACCESS_KEY_ID')
//    static final def AWS_SECRET_ACCESS_KEY    = credentials('AWS_SECRET_ACCESS_KEY')
//    static final def TOMCAT_USERNAME          = credentials('TOMCAT_USERNAME')
//    static final def TOMCAT_PASSWORD          = credentials('TOMCAT_PASSWORD')
//    static final def WUM_UAT_URL              = credentials('WUM_UAT_URL')
//    static final def WUM_UAT_APP_KEY          = credentials('WUM_UAT_APPKEY')
//    static final def USER_NAME                = credentials('WUM_USERNAME')
//    static final def PASSWORD                 = credentials('WUM_PASSWORD')
//    static final def GIT_WUM_USERNAME         = credentials('GIT_WUM_USERNAME')
//    static final def GIT_WUM_PASSWORD         = credentials('GIT_WUM_PASSWORD')
//    static final def CURRENT_WORKSPACE        = pwd()
//    static final def JOB_CONFIG_YAML          = "job-config.yaml"
//    static final def JOB_CONFIG_YAML_PATH     = "${PWD}/${JOB_CONFIG_YAML}"
//    static final def PRODUCT_GIT_URL          = "${PRODUCT_GIT_URL}"
//    static final def PRODUCT_GIT_BRANCH       = "${PRODUCT_GIT_BRANCH}"
//    static final def PRODUCT_DIST_DOWNLOAD_API= "${PRODUCT_DIST_DOWNLOAD_API}"
//    static final def WUM_CHANNEL              = "${WUM_CHANNEL}"
//    static final def PRODUCT_CODE             = "${PRODUCT_CODE}"
//    static final def WUM_PRODUCT_VERSION      = "${WUM_PRODUCT_VERSION}"
//    static final def USE_CUSTOM_TESTNG        = "${USE_CUSTOM_TESTNG}"
//    static final def EXECUTOR_COUNT           = "${EXECUTOR_COUNT}"

//    static def props = [
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

//    static def configProps = []
//
//    // Returns a requested property.
//    static def getProperty(property) {
//        return configProps.get(property)
//    }
//}
