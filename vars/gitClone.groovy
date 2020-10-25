#!/usr/bin/env groovy

import java.net.URL
import java.net.URI
import java.nio.file.Paths
import java.nio.file.Path

def call(final Map data) {
    call(data.remoteUrl, data.branch, data.removeIfPresent, data.gitExe)
}

def call(String remoteUrl, String branch, boolean removeIfPresent = true, String gitExe = "git") {
    def url = Paths.get(new URL(remoteUrl).getFile()).toString()
    blueEcho "remove folder ${url}"
    // fileOperations {
    //     folderDeleteOperation(url.toString())
    // }
    bat(
        script: "${gitExe} clone --single-branch --branch ${branch} ${remoteUrl}",
        label: "git clone ${branch} from ${remoteUrl}"
    )
}