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
def call(Iterable<String> entries, Iterable<String> excludePatterns) {

    def result = new ArrayList<String>()
    for (var x : entries) {
        var excluded = false
        for (var excludeRegex : excludePatterns) {
            if (Pattern.matches(excludeRegex, x)) {
                excluded = true
                break
            }
        }

        if (!excluded) {
            result.Add(x)
        }
    }

    return result
}

def call(final Map data) {
    call(data.entries, data.excludePatterns)
}