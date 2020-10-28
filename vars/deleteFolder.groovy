#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8.0')
import org.apache.commons.io.FileUtils
import java.nio.file.Paths
import java.nio.file.Files

@NonCPS
def call(Map data) {
    call(data.file)
}

@NonCPS
def call(String file) {
    try {
        echo "Deleting folder ${file}"
        Files.delete(Paths.get(file).toFile())
    } catch (IOException ioException) {
        //ignore if the file does not exist
    }
}