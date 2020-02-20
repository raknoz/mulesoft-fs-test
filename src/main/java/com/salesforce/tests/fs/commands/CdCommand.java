package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.EnvironmentScope;
import com.salesforce.tests.fs.File;
import com.salesforce.tests.fs.PathHelper;
import com.salesforce.tests.fs.Response;

import java.util.Optional;

public class CdCommand implements Command {
    @Override
    public Response execute(final EnvironmentScope env, final String parameter) {
        final Optional<File> optionalFile = PathHelper.getPath(env, parameter);
        return optionalFile.map(file -> new Response(file, "")).orElse(new Response(env.getCurrent(), "Directory not found"));
    }
}
