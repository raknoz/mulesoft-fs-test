package com.salesforce.tests.fs;

public class Response {
    private File currentFile;
    private String output;

    public Response(final File currentFile, final String output) {
        this.currentFile = currentFile;
        this.output = output;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public String getOutput() {
        return output;
    }
}
