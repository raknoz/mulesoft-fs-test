package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.EnvironmentScope;
import com.salesforce.tests.fs.File;
import com.salesforce.tests.fs.PathHelper;
import com.salesforce.tests.fs.PathSplitter;
import com.salesforce.tests.fs.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class TouchCommand implements Command {
    @Override
    public Response execute(final EnvironmentScope env, final String parameter) {

        if (nonNull(parameter) && parameter.length() > 100) {
            return new Response(env.getCurrent(), "The name of file is too long");
        }
        final PathSplitter pathSplitter = PathHelper.resolvePath(parameter);
        final Optional<File> pathExecute = PathHelper.getPath(env, pathSplitter.getPath().orElse("."));
        if (!pathExecute.isPresent()) {
            return new Response(env.getCurrent(), "Directory not found");
        }
        final List<File> files = pathExecute.get().getFiles();
        if (files.stream().anyMatch(file -> file.getName().equals(pathSplitter.getResource()))) {
            return new Response(env.getCurrent(), "");
        }
        final File file = new File(false, pathSplitter.getResource(), pathExecute, Collections.emptyList());
        files.add(file);
        pathExecute.get().setFiles(files);

        return new Response(env.getCurrent(), "");
    }
}
