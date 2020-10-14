#!/usr/bin/env groovy

def call(final Map data) {
    call(data.remoteUrl, data.gitExe)
}

def call(String remoteUrl, String gitExe = "git") {
    bat(
        script: "${gitExe} push ${remoteUrl}",
        label: "pushing to ${remoteUrl}"
    )
}