#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8')
import org.apache.commons.io.FileUtils
import java.nio.file.Paths

def call(Map data) {
    call(data.source, data.target)
}

def call(Path source, Path target) {
    echo "Coping file ${source} to ${target}"
    FileUtils.copyFile(source.toFile(), target().toFile())
}