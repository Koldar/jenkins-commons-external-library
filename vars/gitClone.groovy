#!/usr/bin/env groovy

def call(final Map data) {
    call(data.remoteUrl, data.branch, data.gitExe)
}

def call(String remoteUrl, String branch, String gitExe = "git") {
    bat(
        script: "${gitExe} clone --single-branch --branch ${branch} ${remoteUrl}",
        label: "git clone ${branch} from ${remoteUrl}"
    )
}