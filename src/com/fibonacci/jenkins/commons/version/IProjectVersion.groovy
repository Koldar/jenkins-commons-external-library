package com.fibonacci.jenkins.commons.version

import com.fibonacci.jenkins.commons.ReleaseType

interface ISystemInterface {

    String fetchCurrentProjectVersion()
    void updateProjectVersion(String version, ReleaseType releaseType)
}