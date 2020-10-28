#!/usr/bin/env groovy

import java.nio.file.Paths

@NonCPS
def call(String first, String... other) {
    return Paths.get(first, other).toAbsolutePath().toString()
}