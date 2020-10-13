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

    try {
        body()
    } catch (Exception e) {
        redEcho "Erorr has occured ${e.message}"
        def w = new StringWriter()
        e.printStackTrace(new PrintWriter(w))
        redEcho "${w}"
        throw e;
    }
    
}

def call(final Map data) {
    call(data.entries, data.excludePatterns)
}