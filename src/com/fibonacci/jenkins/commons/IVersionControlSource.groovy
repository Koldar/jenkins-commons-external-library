package com.fibonacci.jenkins.commons

interface IVersionControlSource {

    String getLatestTagName()

    String getCurrentCommit()

    String[] getCommitLog(String from, String to) 
}