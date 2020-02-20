package com.salesforce.tests.fs;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class PathHelper {

    public static PathSplitter resolvePath(final String path) {
        final int index = path.lastIndexOf("/");
        if (index == -1) {
            return new PathSplitter(path, Optional.empty());
        }
        final String nameFile = path.substring(index + 1);
        final String pathFile = path.substring(0, index);
        return new PathSplitter(nameFile, Optional.of(pathFile));
    }

    public static Optional<File> getPath(final EnvironmentScope env, final String path) {
        final List<String> directories = Arrays.asList(path.split("/"));
        if (path.isEmpty()) {
            return Optional.of(env.getCurrent());
        }
        if (directories.size() == 0) {
            return Optional.of(env.getRoot());
        }
        final Optional<String> one = directories.stream().findFirst();
        switch (one.get()) {
            case "":
            case ".":
                return getPath(env, String.join("/", directories.subList(1, directories.size())));
            case "..":
                return getPath(
                        new EnvironmentScope(env.getRoot(), env.getCurrent().getParent().orElse(env.getCurrent())),
                        String.join("/", directories.subList(1, directories.size())));
            default:
                Optional<File> newCurrent = findFolder(env.getCurrent(), one.get());
                if (newCurrent.isPresent()) {
                    return getPath(
                            new EnvironmentScope(env.getRoot(), newCurrent.get()), String.join("/",
                                    directories.subList(1, directories.size())));
                }
                return Optional.empty();
        }
    }

    private static Optional<File> findFolder(final File file, final String nameFile) {
        return file.getFiles().stream().filter(f -> f.getName().equals(nameFile) && f.isDirectory()).findFirst();
    }
}
