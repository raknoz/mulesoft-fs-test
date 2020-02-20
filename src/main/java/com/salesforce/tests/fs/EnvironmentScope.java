package com.salesforce.tests.fs;

public class EnvironmentScope {
    private File root;
    private File current;

    public EnvironmentScope(final File root, final File current) {
        this.root = root;
        this.current = current;
    }

    public void setCurrent(final File current) {
        this.current = current;
    }

    public File getRoot() {
        return root;
    }

    public File getCurrent() {
        return current;
    }
}
