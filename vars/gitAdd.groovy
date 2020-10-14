#!/usr/bin/env groovy

def call(final Map data) {
    call(data.files, data.gitExe)
}

def call(Iterable<String> files, String gitExe = "git") {
    for (def f: files) {
        bat(
        script: "${gitExe} add ${f}",
        label: "add ${f}"
        )
    }
}