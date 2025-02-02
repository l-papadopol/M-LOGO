
/*
 * Command Executor
 * it executes commands. a class really self-explaing
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package model.commands;

import view.DrawingArea;

import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

import model.entities.Turtle;

public class CommandExecutor {

    public static void executeCommand(String command, Turtle turtle, DrawingArea window, Map<String, List<String>> procedures, JTextArea logArea) {
        // Use CommandParser to parse the command string into a Command object.
        Command cmd = CommandParser.parseCommand(command, turtle, procedures, logArea, window);
        if (cmd != null) {
            try {
                cmd.execute();
            } catch (Exception e) {
                logArea.append("Error executing command: " + command + "\n");
            }
        } else {
            logArea.append("Invalid command.\n");
        }
        window.update();
    }

    public static void executeProcedure(String procedureName, Map<String, List<String>> procedures, Turtle turtle, DrawingArea window, JTextArea logArea) {
        List<String> commands = procedures.get(procedureName);
        if (commands == null) {
            logArea.append("Error: procedure '" + procedureName + "' not found.\n");
            return;
        }

        logArea.append("Executing procedure '" + procedureName + "':\n");
        for (String command : commands) {
            executeCommand(command, turtle, window, procedures, logArea);
        }
    }

}
