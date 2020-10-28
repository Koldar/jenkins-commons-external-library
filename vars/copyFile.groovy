#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8')
import org.apache.commons.io.FileUtils
import java.nio.file.Paths

@NonCPS
def call(Map data) {
    call(data.source, data.target)
}

@NonCPS
def call(String source, String target) {
    echo "Coping file ${source} to ${target}"
    FileUtils.copyFile(Paths.get(source).toFile(), Paths.get(target).toFile())
}