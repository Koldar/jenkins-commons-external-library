#!/usr/bin/env groovy

import java.nio.file.Paths

@NonCPS
def call(String... other) {
    return Paths.get(other).toString()
}