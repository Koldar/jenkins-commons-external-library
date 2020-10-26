#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.ReleaseType
import com.fibonacci.jenkins.commons.VisualStudioProjectVersion

def call(final Map data) {
    return call(data.visualStudioProjectFileXml, data.newVersion, data.releaseType)
}

def call(String visualStudioProjectFileXml, String newVersion, ReleaseType releaseType) {
    def vsof = new VisualStudioProjectVersion(visualStudioProjectFileXml)
    vsof.updateProjectVersion(newVersion, releaseType)
}