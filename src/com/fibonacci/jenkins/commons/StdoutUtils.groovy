package com.fibonacci.jenkins.commons;

class StdoutUtils {

    static String[] getBatOutput(String output) {
        output = output.trim(); //remove trailing lines
        def l = output.readLines()
        //the first line is always the command input itself
        l.removeAt(0);
        def result = []
        for (def i in l) {
            result.add(i.trim())
        }
        echo("getBatOutputis ${result}")
        return result
    }

    static String getSingleLineBatOutput(String output) {
        return getBatOutput(output)[0]
    }

}
