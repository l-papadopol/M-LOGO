/*
 * M-LOGO
 * a very simple LOGO language interpreter for educational use
 *(C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package controller;
import javax.swing.*;

import view.LogoView;
import view.DrawingArea;
import model.commands.*;
import model.entities.Turtle;

public class LogoController {
    // ProcedureManager now handles the entire procedure storage
    private static ProcedureManager procedureManager = new ProcedureManager();

    public static void main(String[] args) {
        Turtle turtle = new Turtle(400, 400);

        // Create the view (LogoView now handles all view logic)
        LogoView view = new LogoView(turtle);
        view.show();

        // Register a listener for command input
        view.addCommandListener(e -> {
            String command = view.getCommand().toUpperCase().trim();
            view.clearCommandInput();

            if (command.isEmpty()) return;

            if (procedureManager.isDefiningProcedure()) {
                // We are defining a procedure, so we add commands
                handleProcedureInput(command, procedureManager, view.getLogArea());
            } else {
                // Normal command handling
                handleCommandInput(command, procedureManager, turtle, view.getDrawingWindow(), view.getLogArea());
            }
        });
    }

    // Command input handler
    private static void handleCommandInput(String command, ProcedureManager procedureManager, Turtle turtle, DrawingArea window, JTextArea logArea) {
        if (command.equals("EXIT")) {
            logArea.append("Closing the program...\n");
            System.exit(0);
        } else if (command.startsWith("TO")) {
            startProcedureDefinition(command, procedureManager, logArea);
        } else if (procedureManager.containsProcedure(command)) {
            executeProcedure(command, procedureManager, turtle, window, logArea);
        } else {
            // Command validation before execution using the internal procedures map
            if (CommandValidator.validateCommand(command, procedureManager.getProcedures())) {
                executeCommand(command, turtle, window, procedureManager.getProcedures(), logArea);
            } else {
                logArea.append("Invalid command: " + command + "\n");
            }
        }
    }

    // Define a procedure which is a block of commands grouped and labeled so user can
    // reuse it as "building block"
    private static void startProcedureDefinition(String command, ProcedureManager procedureManager, JTextArea logArea) {
        String procedureName = command.substring(3).trim();
        if (procedureName.isEmpty()) {
            logArea.append("Error: specify a name for the procedure.\n");
            return;
        }
        procedureManager.startProcedure(procedureName, logArea);
    }

    // How a procedure input process is handled? This way here down!
    private static void handleProcedureInput(String command, ProcedureManager procedureManager, JTextArea logArea) {
        if (command.equals("END")) {
            // Finish the procedure using the manager's internal storage
            procedureManager.finishProcedure(logArea);
        } else {
            // Command validation and addition
            if (CommandValidator.validateCommand(command, procedureManager.getProcedures())) {
                procedureManager.addCommand(command, logArea);
            } else {
                logArea.append("Invalid command, try again.\n");
            }
        }
    }
    
    // How a procedure is executed? Here the handling of this task!
    private static void executeProcedure(String procedureName, ProcedureManager procedureManager, Turtle turtle, DrawingArea window, JTextArea logArea) {
        CommandExecutor.executeProcedure(procedureName, procedureManager.getProcedures(), turtle, window, logArea);
    }

    // How a command is executed? Here the handling of this task!
    private static void executeCommand(String command, Turtle turtle, DrawingArea window, java.util.Map<String, java.util.List<String>> procedures, JTextArea logArea) {
        CommandExecutor.executeCommand(command, turtle, window, procedures, logArea);
    }
}
