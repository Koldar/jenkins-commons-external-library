#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils

def call(final Map data) {
    return doWork(data.entries, data.path)
}

def doWork(String[] entries, String path) {
    def today = new Date().format("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC")) 

    def changeLogContent = []
    changeLogContent.add("CHANGELOG")
    changeLogContent.add("This file represents the features, bugfix and so on that have been done from tag ${lastTagName} to tag ${tagName}")
    changeLogContent.add("This file has been generated ${today}")
    changeLogContent.add("")
    entries.indexed().collect { 
        index, item -> changeLogContent.add(" - ${index}: ${item}") 
    }

    echo("#lines in changeLogContent = ${changeLogContent.size()}")
    writeFile(file: path, text: "${changeLogContent.join('\n')}", encoding: "utf8")
}