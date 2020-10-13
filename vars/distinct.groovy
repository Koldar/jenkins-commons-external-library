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
def call(Iterable entries) {
    return entries.stream().distinct().collect()
}

def call(final Map data) {
    call(data.entries)
}