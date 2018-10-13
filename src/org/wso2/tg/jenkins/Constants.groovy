package org.wso2.tg.jenkins

/**
 * Constants used in runtime.
 */
class Constants {

    def envVars = Jenkins.instance.getGlobalNodeProperties()[0].getEnvVars()


    static final def TESTGRID_NAME            = "TESTGRID_NAME"
    static final def TESTGRID_DIST_LOCATION   = "TESTGRID_DIST_LOCATION"
    static final def TESTGRID_HOME            = "TESTGRID_HOME"
    static final def PRODUCT                  = "XX"//env.getProperty("JOB_BASE_NAME")
    static final def TESTGRID_YAML_LOCATION   = "TESTGRID_YAML_LOCATION"
    static final def AWS_ACCESS_KEY_ID        = "AWS_ACCESS_KEY_ID"
    static final def AWS_SECRET_ACCESS_KEY    = "AWS_SECRET_ACCESS_KEY"
    static final def TOMCAT_USERNAME           = "tomcatUsername"
    static final def TOMCAT_PASSWORD           = "tomcatPassword"
    static final def WUM_UAT_URL              = "WUM_UAT_URL"
    static final def WUM_UAT_APP_KEY           = "WUM_UAT_APPKEY"
    static final def USER_NAME                = "USER_NAME"
    static final def PASSWORD                 = "PASSWORD"
    static final def GIT_WUM_USERNAME         = "GIT_WUM_USERNAME"
    static final def GIT_WUM_PASSWORD         = "GIT_WUM_PASSWORD"
    static final def CURRENT_WORKSPACE        = "PWD"
    static final def JOB_CONFIG_YAML          = "JOB_CONFIG_YAML"
    static final def JOB_CONFIG_YAML_PATH     = "JOB_CONFIG_YAML_PATH"
    static final def PRODUCT_GIT_URL          = "PRODUCT_GIT_URL"
    static final def PRODUCT_GIT_BRANCH       = "PRODUCT_GIT_BRANCH"
    static final def PRODUCT_DIST_DOWNLOAD_API= "PRODUCT_DIST_DOWNLOAD_API"
    static final def WUM_CHANNEL              = "WUM_CHANNEL"
    static final def PRODUCT_CODE             = "PRODUCT_CODE"
    static final def WUM_PRODUCT_VERSION      = "WUM_PRODUCT_VERSION"
    static final def USE_CUSTOM_TESTNG        = "USE_CUSTOM_TESTNG"
    static final def EXECUTOR_COUNT           = "EXECUTOR_COUNT"
}
