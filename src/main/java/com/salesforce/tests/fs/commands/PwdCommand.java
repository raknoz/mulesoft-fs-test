package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.EnvironmentScope;
import com.salesforce.tests.fs.Response;

public class PwdCommand implements Command {
    @Override
    public Response execute(final EnvironmentScope env, final String parameter) {
        return new Response(env.getCurrent(), env.getCurrent().path());
    }
}
