#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.StdoutUtils

def call(final Map data) {
    return call(data.visualStudioProjectFileXml)
}

def call(String visualStudioProjectFileXml) {
    def file = new File(visualStudioProjectFileXml)
    def project = new XmlParser().parse(file)
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