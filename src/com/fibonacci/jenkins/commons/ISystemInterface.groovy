package com.fibonacci.jenkins.commons

interface ISystemInterface {
    
    void executeCommand(String cmd)

    String getCommandOneLiner(String cmd)

    String[] getCommandMultiLine(String cmd)
}