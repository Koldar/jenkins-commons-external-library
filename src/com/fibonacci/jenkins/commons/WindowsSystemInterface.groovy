package com.fibonacci.jenkins.commons

public class WindowsSystemInterface implements ISystemInterface {
    
    void executeCommand(String cmd) {
    }

    String getCommandOneLiner(String cmd) {
        return ""
    }

    String[] getCommandMultiLine(String cmd) {
        return []
    }
}