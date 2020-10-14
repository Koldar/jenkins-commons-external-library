#!/usr/bin/env groovy

def call(final Map data) {
    call(data.message, data.gitExe)
}

def call(String message, String gitExe = "git") {
    bat(
        script: "${gitExe} commit -m \"${message}\"",
        label: "commit ${message}"
    )
}