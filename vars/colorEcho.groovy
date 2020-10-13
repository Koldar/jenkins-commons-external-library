#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.AnsiColors;

def call(String message, String color = "default") {
    echo AnsiColors.getColorMessage(message, color)
}