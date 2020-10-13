#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils;

def call(final Map data) {
    return call(data.script, data.label)
}

/**
 * call bat step and automatically convert the output into a sequence of lines
 */
def call(String script, String label) {
    def stdout = bat(
        script: script,
        label: label,
        returnStdout: true
    )
    return StdoutUtils.getBatOutput(stdout)
}