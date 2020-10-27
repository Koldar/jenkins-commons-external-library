#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.AnsiColors;

def call(String message) {
    ansiColor("xterm") {
        echo AnsiColors.getColorMessage(message, "yellow")
    }
}