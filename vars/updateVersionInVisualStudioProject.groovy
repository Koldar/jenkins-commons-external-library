#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.ReleaseType

def call(final Map data) {
    return call(data.visualStudioProjectFileXml, data.newVersion, data.releaseType)
}

def call(String visualStudioProjectFileXml, String newVersion, ReleaseType releaseType) {
    def file = new File(visualStudioProjectFileXml)
    def project = new XmlParser().parse(file)
    def propertyGroups = project.children().findAll {
        //echo("name=${it.name().getLocalPart()}")
        return it.name().getLocalPart() == "PropertyGroup"
    }
    for (propertyGroup in propertyGroups) {
        if (propertyGroup.attributes().containsKey("Label") && propertyGroup.attributes().get("Label") == "Versions") {
            //update version
            def versionPrefix = propertyGroup.children().find { return it.name().getLocalPart() == "VersionPrefix"}
            echo("Old VersionPrefix is ${versionPrefix.text()}")
            versionPrefix.setValue(newVersion)
            echo("New VersionPrefix is ${versionPrefix.text()}")
            //update version suffix
            def newSuffix = releaseType.getSuffix()
            def versionSuffix = propertyGroup.children().find { return it.name().getLocalPart() == "VersionSuffix"}
            echo("Old VersionSuffix is ${versionSuffix.text()}")
            versionSuffix.setValue(newSuffix)
            echo("New VersionSuffix is ${versionSuffix.text()}")
            break
        }
    }
    //save new xml
    new XmlNodePrinter(new PrintWriter(new FileWriter(visualStudioProjectFileXml))).print(project)

    //ensure the change has been made
    def versionCheck = fetchVersionFromVisualStudio(visualStudioProjectFileXml)
    echo("When retying to read the project file, we got a version of ${versionCheck}")
    if (versionCheck != newVersion) {
        throw new IllegalArgumentException("version ${newVersion} has not been updated!")
    }
}