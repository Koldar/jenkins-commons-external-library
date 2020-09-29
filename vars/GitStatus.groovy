#!/usr/bin/env groovy

//stepimplementatyion. See https://tomd.xyz/jenkins-shared-library/

def call(String gitExe) {
    bat(
        script: "${gitExe} status",
        label: "get the branch currently checked out"
    )
}