#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils
import java.lang.Iterable
import java.util.List
import java.util.ArrayList
import java.nio.file.Paths
import java.util.regex.Pattern
import package com.fibonacci.jenkins.commons.MatrixUtils

/**
 * generate a changelog file
 * 
 * @param entries the values to put in the changelog
 * @param path the changelog path to create (relative to workspace)
 * @param startTag the tag where the changelog starts
 * @param endTag the tag were the changellog ends
 */
def call(List combinations, boolean shouldRunParallel, Closure f) {

    return MatrixUtils.runCombinations(combinations, shouldRunParallel) {
        node {
            withEnv(cenv) {
                closure(combination)
            }
        }
    }
}

def call(final Map data) {
    call(data.combinations, data.shouldRunParallel, data.f)
}