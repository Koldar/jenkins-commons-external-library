#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils;

def call(final Map data) {
    return call(data.command, data.label)
}

/**
 * call bat step and automatically convert the output into a sequence of lines
 */
def call(String command, String label) {
    def stdout = bat(
        script: command,
        label: label,
        returnStdout: true
    )
    return StdoutUtils.getBatOutput(stdout)
}