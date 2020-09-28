package com.fibonacci.jenkins.commons;

class ExternalCommandsUtils {

    static void getBatOutput(String output) {
        output = output.trim();
        def l = output.readLines()
        //the first line is always the command input itself
        l.removeAt(0);
        return l
    }

}
