#!/usr/bin/env groovy

def call(final Map data) {
    return call(data.branchToSet, data.remoteUrl, data.gitExe ?: "git")
}

def call(String branchToSet, String remoteUrl, String gitExe = "git") {
    echo "Reattach HEAD of git"
    bat(
        script: "${gitExe} status",
        label: "get the branch currently checked out"
    )
    bat(
        script: "${gitExe} checkout ${branchToSet}",
        label: "checkout"
    )
    bat(
        script: "${gitExe} pull ${remoteUrl}",
        label: "pull"
    )
}