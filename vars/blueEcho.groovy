#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.AnsiColors;

def call(String message) {
    ansiColor("vga") {
        echo AnsiColors.getColorMessage(message, "blue")
    }
}