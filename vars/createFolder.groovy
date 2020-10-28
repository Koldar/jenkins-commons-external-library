#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8')
import org.apache.commons.io.FileUtils
import java.nio.file.Paths

@NonCPS
def call(Map data) {
    call(data.folder)
}

@NonCPS
def call(Path folder) {
    echo "Creating folder ${folder}"
    Files.createDirectories(folder.toFile())
}