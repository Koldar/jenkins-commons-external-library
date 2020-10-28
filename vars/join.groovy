#!/usr/bin/env groovy

import java.nio.file.Paths

def call(String... other) {
    return Paths.get(other).toString()
}