#!/usr/bin/env groovy

def call(final Map data) {
    call(data.remoteUrl, data.branch, data.gitExe, data.removeIfPresent)
}

def call(String remoteUrl, String branch, bool removeIfPresent = true, String gitExe = "git") {
    blueEcho "remove folder ${new Url(remoteUrl)}"
    fileOperations {
        folderDeleteOperation(folderPath: )
    }
    bat(
        script: "${gitExe} clone --single-branch --branch ${branch} ${remoteUrl}",
        label: "git clone ${branch} from ${remoteUrl}"
    )
}