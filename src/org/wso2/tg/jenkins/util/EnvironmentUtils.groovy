package org.wso2.tg.jenkins.util

import hudson.AbortException


class EnvironmentUtils implements Serializable {

    static String getEnvironmentVariable(script, variable) {
        try {
          def envVar = script.sh returnStdout: true, script: """#!/bin/bash --login
                                                                echo \$$variable"""
          return envVar.trim()
        } catch(AbortException e) {
          throw new AbortException("Error while retrieving the environment variable '$variable'. Reason: $e.message.")
        }
    }
}
