package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.EnvironmentScope;
import com.salesforce.tests.fs.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LsCommand implements Command {
    @Override
    public Response execute(final EnvironmentScope env, final String parameter) {
        List<String> names = new ArrayList<>(Collections.singletonList(".."));
        if (env.getCurrent().isDirectory()) {
            env.getCurrent().getFiles().forEach(file ->
                    names.add("\u001B[34m" + file.getName() + "\u001B[00m"));
        }
        return new Response(env.getCurrent(), String.join("\n", names));
    }
}