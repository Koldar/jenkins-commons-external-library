#!/usr/bin/env groovy

import java.net.URL

def call(final Map data) {
    call(data.remoteUrl, data.branch, data.gitExe, data.removeIfPresent)
}

def call(String remoteUrl, String branch, boolean removeIfPresent = true, String gitExe = "git") {
    def url = new URL(remoteUrl)
    blueEcho "remove folder ${url}"
    fileOperations {
        folderDeleteOperation(folderPath: url.toString())
    }
    bat(
        script: "${gitExe} clone --single-branch --branch ${branch} ${remoteUrl}",
        label: "git clone ${branch} from ${remoteUrl}"
    )
}