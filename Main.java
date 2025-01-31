/*
 * M-LOGO
 * a very simple LOGO language interpreter for educational use
 *(C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

import javax.swing.*;
import commands.CommandExecutor;
import commands.CommandValidator;
import entities.Window;
import entities.Turtle;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
    private static boolean definingProcedure = false;
    private static String currentProcedureName = "";
    private static List<String> currentProcedureCommands = new ArrayList<>();

    public static void main(String[] args) {
        Map<String, List<String>> procedures = new HashMap<>();
        Turtle turtle = new Turtle(400, 400);
        Window window = new Window(turtle);

        JFrame frame = new JFrame("M-LOGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(window, BorderLayout.CENTER);

        JPanel inputPanel = createInputPanel(turtle, window, procedures);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setSize(800, 900);
        frame.setVisible(true);
    }

    // I really don't know how I've made this... I've just tried till the GUI appeared looking "almost good"
    private static JPanel createInputPanel(Turtle turtle, Window window, Map<String, List<String>> procedures) {
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextArea logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        inputPanel.add(scrollPane, BorderLayout.NORTH);

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(commandPanel, BorderLayout.CENTER);

        JTextField inputField = new JTextField(50);
        commandPanel.add(inputField);
        JButton sendButton = new JButton("Send Command");
        commandPanel.add(sendButton);

        sendButton.addActionListener(e -> {
            String command = inputField.getText().toUpperCase().trim();
            inputField.setText("");

            if (command.isEmpty()) return;

            if (definingProcedure) {
                // We are defining a procedure, so we add commands
                handleProcedureInput(command, procedures, logArea);
            } else {
                // Normal command handling
                handleCommandInput(command, procedures, turtle, window, logArea);
            }
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });

        inputField.addActionListener(e -> sendButton.doClick());
        logArea.append("Welcome! Here are the available commands:\n");
        showMenu(logArea);
        return inputPanel;
    }

    // Command input handler
    private static void handleCommandInput(String command, Map<String, List<String>> procedures, Turtle turtle, Window window, JTextArea logArea) {
        if (command.equals("EXIT")) {
            logArea.append("Closing the program...\n");
            System.exit(0);
        } else if (command.startsWith("TO")) {
            startProcedureDefinition(command, logArea);
        } else if (procedures.containsKey(command)) {
            executeProcedure(command, procedures, turtle, window, logArea);
        } else {
            // Command validation before execution
            if (CommandValidator.validateCommand(command, procedures)) {
                executeCommand(command, turtle, window, procedures, logArea);
            } else {
                logArea.append("Invalid command: " + command + "\n");
            }
        }
    }

    // Define a procedure wich is a block of commands grouped and labeled so user can
    // reuse it as "building block"
    private static void startProcedureDefinition(String command, JTextArea logArea) {
        String procedureName = command.substring(3).trim();
        if (procedureName.isEmpty()) {
            logArea.append("Error: specify a name for the procedure.\n");
            return;
        }

        definingProcedure = true;
        currentProcedureName = procedureName;
        currentProcedureCommands.clear();

        logArea.append("Procedure definition '" + procedureName + "' started.\n");
        logArea.append("Enter commands, type 'END' to finish.\n");
    }

    // How a procedure input process is handled? This way here down!
    private static void handleProcedureInput(String command, Map<String, List<String>> procedures, JTextArea logArea) {
        if (command.equals("END")) {
            // Finish the procedure
            if (currentProcedureCommands.isEmpty()) {
                logArea.append("Error: the procedure '" + currentProcedureName + "' is empty and was not saved.\n");
            } else {
                procedures.put(currentProcedureName, new ArrayList<>(currentProcedureCommands));
                logArea.append("Procedure '" + currentProcedureName + "' successfully defined.\n");
            }
            definingProcedure = false;
            currentProcedureName = "";
            currentProcedureCommands.clear();
        } else {
            // Command validation and addition
            if (CommandValidator.validateCommand(command, procedures)) {
                currentProcedureCommands.add(command);
                logArea.append("Added to procedure: " + command + "\n");
            } else {
                logArea.append("Invalid command, try again.\n");
            }
        }
    }
    
    
    // How a procedure is executed? Here the handling of this task!
    private static void executeProcedure(String procedureName, Map<String, List<String>> procedures, Turtle turtle, Window window, JTextArea logArea) {
        CommandExecutor.executeProcedure(procedureName, procedures, turtle, window, logArea);
    }

    // How a command is executed? Here the handling of this task!
    private static void executeCommand(String command, Turtle turtle, Window window, Map<String, List<String>> procedures, JTextArea logArea) {
        CommandExecutor.executeCommand(command, turtle, window, procedures, logArea);
    }

    // Maybe this should be moved or become a new command
    private static void showMenu(JTextArea logArea) {
        logArea.append("Available commands:\n");
        logArea.append("FORWARD <distance> - Moves the turtle forward\n");
        logArea.append("BACK <distance> - Moves the turtle backward\n");
        logArea.append("RIGHT <degrees> - Rotates the turtle to the right\n");
        logArea.append("LEFT <degrees> - Rotates the turtle to the left\n");
        logArea.append("PENUP - Lifts the pen\n");
        logArea.append("PENDOWN - Lowers the pen\n");
        logArea.append("THICKNESS <thickness> - Sets the line thickness\n");
        logArea.append("SETCOLOR <color> - Changes the pen color (e.g., RED, BLUE, GREEN)\n");
        logArea.append("TO <name> - Starts defining a procedure\n");
        logArea.append("END - Finishes defining a procedure\n");
        logArea.append("EXIT - Exits the program\n");
    }
}
