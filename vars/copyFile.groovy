#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8.0')
import org.apache.commons.io.FileUtils
import java.nio.file.Paths

@NonCPS
def call(Map data) {
    call(data.source, data.target)
}

@NonCPS
def call(String source, String target) {
    def s = Paths.get(source)
    def t = Paths.get(target, s.getFileName().toString())
    echo "Coping file ${s} to ${t}"
    FileUtils.copyFile(s.toFile(), t.toFile())
}