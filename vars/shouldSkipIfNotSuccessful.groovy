#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths

/**
 * a script that if raise exception, it is caught
 * 
 * 
 */
def call(Closure body) {

    if (currentBuild.currentResult == "SUCCESS") {
        body()
    }
    
}

def call(final Map data) {
    call(data.steps)
}