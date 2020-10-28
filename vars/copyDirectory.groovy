#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8')
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.RegexFileFilter
import java.nio.file.Paths

def call(Map data) {
    call(data.sources, data.target, data.filter)
}

def internalCall(Path source, Path target, IOFileFilter filter) {
    echo "Copy Directory ${source} into ${target} using filter ${filter}"
    FileUtils.copyDirectory(
        source.toFile(), 
        target().toFile(),
        FileFilterutils.or(regexes),
        true
    )
}

def call(Path[] sources, Path target, String[] filters) {
    var regexes = []
    for (f in filters) {
        regexes.add(new RegexFileFilter(filter))
    }
    
    for (s in source)
    internalCall(s, target, filters)
}