package com.fibonacci.jenkins.commons.version

import com.fibonacci.jenkins.commons.ReleaseType

class VisualStudioProjectVersion implements IProjectVersion {

    private File propertiesFile

    VisualStudioProjectVersion(String visualStudioProperties) {
        this.propertiesFile = new File(visualStudioProperties)
    }

    String fetchCurrentProjectVersion() {
        def project = new XmlParser().parse(this.propertiesFile)
        def propertyGroups = project.children().findAll {
            //echo("name=${it.name().getLocalPart()}")
            return it.name().getLocalPart() == "PropertyGroup"
        }
        //echo("propertyGroups in the analyzed xml are ${propertyGroups.size()}")
        for (propertyGroup in propertyGroups) {
            //echo("attributes in the propertyGroup under analysis are ${propertyGroup.attributes().size()}")
            if (propertyGroup.attributes().containsKey("Label") && propertyGroup.attributes().get("Label") == "Versions") {
                def text = propertyGroup.children().find { return it.name().getLocalPart() == "VersionPrefix"}.text().trim()
                //echo("Detected current version ${text}!")
                return (String)text
            }
        }
        throw new IllegalArgumentException("could not find property group labeled \"Versions\" in ${visualStudioProjectFileXml}!")
    }

    void updateProjectVersion(String version, ReleaseType releaseType) {
        def project = new XmlParser().parse(this.propertiesFile)
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
        def printer = new XmlNodePrinter(new PrintWriter(new FileWriter(visualStudioProjectFileXml)))
        printer.setPreserveWhitespace(true)
        printer.print(project)

        //ensure the change has been made
        def versionCheck = fetchVersionFromVisualStudio(visualStudioProjectFileXml)
        echo("When retrying to read the project file, we got a version of ${versionCheck}")
        if (versionCheck != newVersion) {
            throw new IllegalArgumentException("version ${newVersion} has not been updated!")
        }
    }
}