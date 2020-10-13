#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths

/**
 * generate a changelog file
 * 
 * @param entries the values to put in the changelog
 * @param path the changelog path to create (relative to workspace)
 * @param startTag the tag where the changelog starts
 * @param endTag the tag were the changellog ends
 */
def call(Iterable<String> entries, Iterable<String> includePatterns) {

    def result = new ArrayList<String>()
    for (def x : entries) {
        def included = false
        for (def includeRegex : includePatterns) {
            if (Pattern.matches(includeRegex, x)) {
                included = true
                break
            }
        }

        if (included) {
            result.add(x)
        }
    }

    return result
}

def call(final Map data) {
    call(data.entries, data.includePatterns)
}