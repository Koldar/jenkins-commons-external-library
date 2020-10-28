#!/usr/bin/env groovy

@Grab('commons-io:commons-io:2.8.0')
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.IOFileFilter
import org.apache.commons.io.filefilter.RegexFileFilter
import org.apache.commons.io.filefilter.FileFilterUtils
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
        target.toFile(),
        filter,
        true
    )
    echo "Done copying directory"
}

@NonCPS
def call(Iterable<String> sources, String target, Iterable<String> filters) {
    def regexes = []
    for (f in filters) {
        regexes.add(new RegexFileFilter(f))
    }
    def finalFilter = FileFilterUtils.or(regexes.toArray(new IOFileFilter[0]))
    def targetDirectory = Paths.get(target).toAbsolutePath()

    echo "Starting sources for loop"    
    for (s in sources) {
        echo "Handling source \"${s}\""
        _internalCall(Paths.get(s), targetDirectory, finalFilter)
    }
    echo "done!"
}