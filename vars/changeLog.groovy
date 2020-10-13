#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList

/**
 * generate a changelog file
 * 
 * @param entries the values to put in the changelog
 * @param path the changelog path to create (relative to workspace)
 * @param startTag the tag where the changelog starts
 * @param endTag the tag were the changellog ends
 */
def call(Iterable<String> entries, String path, String startTag, String endTag) {
    def today = new Date().format("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC")) 

    def changeLogContent = []
    changeLogContent.add("CHANGELOG")
    changeLogContent.add("This file represents the features, bugfix and so on that have been done from tag ${startTag} to tag ${endTag}")
    changeLogContent.add("This file has been generated ${today}")
    changeLogContent.add("")
    entries.indexed().collect { 
        index, item -> changeLogContent.add(" - ${index}: ${item}") 
    }

    echo("#lines in changeLogContent = ${changeLogContent.size()}")
    writeFile(file: path, text: "${changeLogContent.join('\n')}", encoding: "utf8")
    blueEcho("workspace is ${env.WORKSPACE}")
    blueEcho("CHANGELOG available at ${Paths.get(env.WORKSPACE, path).getAbsolutePath()}")
}

def call(final Map data) {
    call(data.entries, data.path, data.startTag, data.endTag)
}