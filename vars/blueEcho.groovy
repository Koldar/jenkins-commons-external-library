#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.AnsiColors;

def call(String message) {
    AnsiColors.useColorEcho(message, "blue")
}