package com.salesforce.tests.fs.commands;


import com.salesforce.tests.fs.EnvironmentScope;
import com.salesforce.tests.fs.Response;

public interface Command {
    Response execute(EnvironmentScope env, String parameter);
}
