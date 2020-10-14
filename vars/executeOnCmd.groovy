#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths

/**
 * scan the iterable and filters out duplicat elements
 *
 */
def call(String cmd, Iterable<List<String,String>> environmentVariables, String label) {
    def actualCmd = ""
    for (def v: environmentVariables) {
        echo "Setting ${v[0]} to ${v[1]}"
        actualCmd = actualCmd + "SET ${v[0]}=${v[1]};"
    }
    actualCmd = actualCmd + cmd
    echo "Executing ${actualCmd}"
    bat(
        script: actualCmd,
        label: label
    )
    echo "Done Executing ${actualCmd}"
    
}

def call(final Map data) {
    call(data.cmd, data.environmentVariables, data.label)
}