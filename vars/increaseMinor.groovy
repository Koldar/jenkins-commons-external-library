#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils

def call(final Map data) {
    return call(data.version)
}

def call(String version) {
    def (major, minor, patch) = version.tokenize(".")
    def patchInt = patch.toInteger();
    return "${major}.${minor}.${patchInt + 1}"
}