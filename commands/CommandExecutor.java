/*
 * Command Executor
 * it executes commands. a class really self-explaing
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package commands;

import java.awt.Color;
import entities.Turtle;
import entities.Window;

import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

public class CommandExecutor {

    public static void executeCommand(String command, Turtle turtle, Window window, Map<String, List<String>> procedures, JTextArea logArea) {
        String[] parts = command.split(" ");
        
        try {
            switch (parts[0]) {
                case "FORWARD":
                    int distance = Integer.parseInt(parts[1]);
                    turtle.moveForward(distance);
                    logArea.append("Moved forward by " + distance + " units.\n");
                    break;
                case "BACK":
                    distance = Integer.parseInt(parts[1]);
                    turtle.moveBack(distance);
                    logArea.append("Moved backward by " + distance + " units.\n");
                    break;
                case "RIGHT":
                    int degrees = Integer.parseInt(parts[1]);
                    turtle.turnRight(degrees);
                    logArea.append("Rotated right by " + degrees + " degrees.\n");
                    break;
                case "LEFT":
                    degrees = Integer.parseInt(parts[1]);
                    turtle.turnLeft(degrees);
                    logArea.append("Rotated left by " + degrees + " degrees.\n");
                    break;
                case "PENUP":
                    turtle.penUp();
                    logArea.append("Pen lifted.\n");
                    break;
                case "PENDOWN":
                    turtle.penDown();
                    logArea.append("Pen lowered.\n");
                    break;
                case "THICKNESS":
                    int thickness = Integer.parseInt(parts[1]);
                    turtle.setThickness(thickness);
                    logArea.append("Line thickness set to " + thickness + ".\n");
                    break;
                case "SETCOLOR":
                    Color color = getColor(parts[1]);
                    turtle.setPenColor(color);
                    logArea.append("Pen color set to " + parts[1] + ".\n");
                    break;
                case "REPEAT":
                    int times = Integer.parseInt(parts[1]);
                    String repeatCommand = command.substring(command.indexOf("[") + 1, command.lastIndexOf("]")).trim();
                    for (int i = 0; i < times; i++) {
                        executeCommand(repeatCommand, turtle, window, procedures, logArea);
                    }
                    break;
                default:
                    logArea.append("Invalid command.\n");
                    break;
            }
        } catch (Exception e) {
            logArea.append("Error in command: " + command + "\n");
        }
        window.update();
    }

    private static Color getColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "RED": return Color.RED;
            case "GREEN": return Color.GREEN;
            case "BLUE": return Color.BLUE;
            case "BLACK": return Color.BLACK;
            default: return Color.BLACK;
        }
    }

    public static void executeProcedure(String procedureName, Map<String, List<String>> procedures, Turtle turtle, Window window, JTextArea logArea) {
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