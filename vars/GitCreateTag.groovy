#!/usr/bin/env groovy

def call(String tagName, String tagDescription, String remoteUrl, String gitExe = "git") {
    bat(
        script: "${gitExe} tag -a \"${tagName}\" -m \"${tagDescription}\"",
        label: "create tag of version ${tagDescription}"
    )
    bat(
        script: "${gitExe} push ${remoteUrl} refs/tags/${tagName}",
        label: "pushing tag ${tagName}"
    )
}