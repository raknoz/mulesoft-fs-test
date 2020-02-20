package com.salesforce.tests.fs;

import java.util.Optional;

public class PathSplitter {
    private String resource;
    private Optional<String> path;

    public PathSplitter(final String resource, final Optional<String> path) {
        this.resource = resource;
        this.path = path;
    }

    public String getResource() {
        return resource;
    }

    public Optional<String> getPath() {
        return path;
    }
}
