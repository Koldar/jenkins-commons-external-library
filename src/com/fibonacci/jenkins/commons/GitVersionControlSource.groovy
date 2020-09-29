package com.fibonacci.jenkins.commons

interface GitVersionControlSource implements IVersionControlSource {

    string getLatestTagName() {
        def lastTagName = bat(
                        script: "git describe --abbrev=0 HEAD~1",
                        returnStdout: true,
                        label: "Get latest tag aside the current one"
                    )
    }

    string getCurrentCommit()

    string[] getCommitLog(string from, string to)
}