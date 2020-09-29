package com.fibonacci.jenkins.commons;

class StdoutUtils {

    static String[] getBatOutput(String output) {
        output = output.trim(); //remove trailing lines
        def l = output.readLines()
        //the first line is always the command input itself
        l.removeAt(0);
        return l
            .stream()
            .map { s -> s.trim()}
            .collect()
    }

    static String getSingleLineBatOutput(String output) {
        return getBatOutput(output)[0]
    }

}
