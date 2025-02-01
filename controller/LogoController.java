package controller;

import commands.CommandExecutor;
import commands.CommandValidator;
import entities.Turtle;
import view.Window;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

/*
 * LogoController is responsible for handling user commands and delegating
 * them to the appropriate logic components. It encapsulates the command processing logic,
 * including validation, execution, and procedure management.
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
public class LogoController {

    private Turtle turtle;
    private Window window;
    private Map<String, List<String>> procedures;
    private JTextArea logArea;

    public LogoController(Turtle turtle, Window window, Map<String, List<String>> procedures, JTextArea logArea) {
        this.turtle = turtle;
        this.window = window;
        this.procedures = procedures;
        this.logArea = logArea;
    }

    /**
     * Processes a command string.
     * If the command is valid, it delegates execution to the CommandExecutor.
     * Otherwise, it logs an error.
     *
     * @param command the command string entered by the user
     */
    public void processCommand(String command) {
        if (CommandValidator.validateCommand(command, procedures)) {
            CommandExecutor.executeCommand(command, turtle, window, procedures, logArea);
        } else {
            logArea.append("Invalid command: " + command + "\n");
        }
    }

    /**
     * Executes a defined procedure by its name.
     *
     * @param procedureName the name of the procedure to execute
     */
    public void executeProcedure(String procedureName) {
        CommandExecutor.executeProcedure(procedureName, procedures, turtle, window, logArea);
    }
}
