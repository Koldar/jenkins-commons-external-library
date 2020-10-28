#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8.0')
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.IOFileFilter
import org.apache.commons.io.filefilter.RegexFileFilter
import java.nio.file.Paths
import java.nio.file.Path

@NonCPS
def call(Map data) {
    call(data.sources, data.target, data.filters)
}

@NonCPS
def _internalCall(Path source, Path target, IOFileFilter filter) {
    echo "Copy Directory ${source} into ${target} using filter ${filter}"
    FileUtils.copyDirectory(
        source.toFile(), 
        target().toFile(),
        filter,
        true
    )
}

@NonCPS
def call(Iterable<String> sources, String target, Iterable<String> filters) {
    def regexes = []
    for (f in filters) {
        regexes.add(new RegexFileFilter(filter))
    }
    def finalFilter = FileFilterutils.or(regexes)
    
    for (s in sources) {
        _internalCall(Paths.get(s), Paths.get(target), finalFilter)
    }
    
}