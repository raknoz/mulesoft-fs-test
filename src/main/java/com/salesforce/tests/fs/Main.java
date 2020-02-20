package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.CdCommand;
import com.salesforce.tests.fs.commands.Command;
import com.salesforce.tests.fs.commands.LsCommand;
import com.salesforce.tests.fs.commands.MkDirCommand;
import com.salesforce.tests.fs.commands.PwdCommand;
import com.salesforce.tests.fs.commands.TouchCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The entry point for the Test program
 */
public class Main {
    private final static String QUIT_CMD = "quit";

    public static void main(String[] args) {
        File fs = new File(true, "/root", Optional.empty(), new ArrayList<>());
        final EnvironmentScope env = new EnvironmentScope(fs, fs);
        final Map<String, Command> mapCommands = new HashMap<>();
        mapCommands.put("pwd", new PwdCommand());
        mapCommands.put("ls", new LsCommand());
        mapCommands.put("mkdir", new MkDirCommand());
        mapCommands.put("cd", new CdCommand());
        mapCommands.put("touch", new TouchCommand());

        for (String cmd : args) {
            final String[] commands = cmd.split(" ");
            String parameter = "";
            if (QUIT_CMD.equals(cmd)) {
                return;
            }
            if (!mapCommands.containsKey(commands[0])) {
                System.err.println("Unrecognized command");
                return;
            }
            if (commands.length > 1) {
                parameter = commands[1];
            }
            final Response response = mapCommands.get(commands[0]).execute(env, parameter);
            env.setCurrent(response.getCurrentFile());
            System.out.println(response.getOutput());
        }
    }
}
