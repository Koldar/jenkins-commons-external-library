#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths

/**
 * scan the iterable and filters out duplicat elements
 *
 */
def call(Iterable<String> entries) {
    def temporary = new HashSet<String>()
    def result = new List<String>()
    for (str : entries) {
        if (temporary.contains(str)) {
            continue
        }
        result.add(str)
        temporary.add(str)
    }
    return result
}

def call(final Map data) {
    call(data.entries)
}