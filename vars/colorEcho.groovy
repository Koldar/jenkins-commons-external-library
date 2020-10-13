#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.AnsiColors;

def call(String message, String color = "default") {
    AnsiColors.useColorEcho(message, color)
}