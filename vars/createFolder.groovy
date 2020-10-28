#!/usr/bin/env groovy

import java.nio.file.Paths
import java.nio.file.Files

@NonCPS
def call(Map data) {
    call(data.folder)
}

@NonCPS
def call(String folder) {
    echo "Creating folder ${folder}"
    def useless = Files.createDirectories(Paths.get(folder).toFile())
    return folder
}