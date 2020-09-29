#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils

def call(String gitExe = "git") {
    def t = bat(
        script: "${gitExe} describe --abbrev=0 HEAD~1",
        returnStdout: true,
        label: "Get latest tag aside the current one"
    )
    return StdoutUtils.getSingleLineBatOutput(t)
}