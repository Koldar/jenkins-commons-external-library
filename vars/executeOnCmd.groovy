#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths

/**
 * scan the iterable and filters out duplicat elements
 * ["JAVA_HOME", JAVA_HOME]
 */
def call(String script, List<List<String>> environmentVariables, String label) {
    def actualCmd = ""
    echo "hello!"
    echo "environmentVariables = ${environmentVariables}"
    for (List<String> v: environmentVariables) {
        echo "Setting ${v.get(0)} to ${v.get(1)}"
        actualCmd = actualCmd + "SET ${v.get(0)}=${v.get(1)};"
    }
    actualCmd = actualCmd + script
    echo "Executing ${actualCmd}"
    bat(
        script: actualCmd,
        label: label
    )
    echo "Done Executing ${actualCmd}"
}

def call(final Map data) {
    call(
        data.script, 
        data.environmentVariables, 
        data.label
    )
}