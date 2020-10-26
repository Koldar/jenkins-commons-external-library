package com.fibonacci.jenkins.commons.version

interface ISystemInterface {

    String fetchCurrentProjectVersion()
    void updateProjectVersion(String version, ReleaseType releaseType)
}