package com.salesforce.tests.fs;

import java.util.List;
import java.util.Optional;

public class File {
    private Boolean directory;
    private String name;
    private Optional<File> parent;
    private List<File> files;

    public File(final Boolean directory, final String name, final Optional<File> parent, final List<File> files) {
        this.directory = directory;
        this.name = name;
        this.parent = parent;
        this.files = files;
    }

    public String path() {
        return getPathParent() + this.name;
    }

    public Boolean isDirectory() {
        return directory;
    }

    private String getPathParent() {
        if (parent.isPresent()) {
            return this.parent.get().path() + "/";
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Optional<File> getParent() {
        return parent;
    }

    public void setParent(final Optional<File> parent) {
        this.parent = parent;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(final List<File> files) {
        this.files = files;

    }
}
