package commands;

import entities.Turtle;
import view.Window;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

/*
 * CommandParser is responsible for parsing a command string and returning
 * the corresponding Command object.
 */
public class CommandParser {

    public static Command parseCommand(String command, Turtle turtle, Map<String, List<String>> procedures, JTextArea logArea, Window window) {
        // Remove extra spaces and assume the command is already in uppercase.
        command = command.trim();
        String[] parts = command.split("\\s+");
        
        if (parts.length == 0) {
            return null;
        }
        
        String cmdType = parts[0];
        try {
            switch (cmdType) {
                case "FORWARD":
                    int distance = Integer.parseInt(parts[1]);
                    // Create a new Forward command and override execute to include logging.
                    return new Forward(turtle, distance) {
                        @Override
                        public void execute() {
                            super.execute();
                            logArea.append("Moved forward by " + distance + " units.\n");
                        }
                    };
                case "BACK":
                    distance = Integer.parseInt(parts[1]);
                    return new Back(turtle, distance) {
                        @Override
                        public void execute() {
                            super.execute();
                            logArea.append("Moved backward by " + distance + " units.\n");
                        }
                    };
                case "RIGHT":
                    int degrees = Integer.parseInt(parts[1]);
                    return new Right(turtle, degrees) {
                        @Override
                        public void execute() {
                            super.execute();
                            logArea.append("Rotated right by " + degrees + " degrees.\n");
                        }
                    };
                case "LEFT":
                    degrees = Integer.parseInt(parts[1]);
                    return new Left(turtle, degrees) {
                        @Override
                        public void execute() {
                            super.execute();
                            logArea.append("Rotated left by " + degrees + " degrees.\n");
                        }
                    };
                case "PENUP":
                    return new Command() {
                        @Override
                        public void execute() {
                            turtle.penUp();
                            logArea.append("Pen lifted.\n");
                        }
                    };
                case "PENDOWN":
                    return new Command() {
                        @Override
                        public void execute() {
                            turtle.penDown();
                            logArea.append("Pen lowered.\n");
                        }
                    };
                case "THICKNESS":
                    int thickness = Integer.parseInt(parts[1]);
                    return new Command() {
                        @Override
                        public void execute() {
                            turtle.setThickness(thickness);
                            logArea.append("Line thickness set to " + thickness + ".\n");
                        }
                    };
                case "SETCOLOR":
                    Color color = getColor(parts[1]);
                    return new Command() {
                        @Override
                        public void execute() {
                            turtle.setPenColor(color);
                            logArea.append("Pen color set to " + parts[1] + ".\n");
                        }
                    };
                case "REPEAT":
                    // Expecting syntax: REPEAT <num> [command]
                    int repeatCount = Integer.parseInt(parts[1]);
                    int startIndex = command.indexOf("[");
                    int endIndex = command.lastIndexOf("]");
                    if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
                        logArea.append("Error: correct syntax -> REPEAT <num> [command]\n");
                        return null;
                    }
                    String subCommand = command.substring(startIndex + 1, endIndex).trim();
                    // If the sub-command is a defined procedure, create a command that repeats it.
                    if (procedures.containsKey(subCommand)) {
                        return new Command() {
                            @Override
                            public void execute() {
                                logArea.append("Executing REPEAT " + repeatCount + " times procedure: " + subCommand + "\n");
                                for (int i = 0; i < repeatCount; i++) {
                                    CommandExecutor.executeProcedure(subCommand, procedures, turtle, window, logArea);
                                }
                            }
                        };
                    } else if (CommandValidator.validateCommand(subCommand, procedures)) {
                        // Otherwise, create a Repeat command using the sub-command string.
                        return new Repeat(repeatCount, subCommand, turtle, window, procedures, logArea);
                    } else {
                        logArea.append("Error: invalid command inside REPEAT.\n");
                        return null;
                    }
                default:
                    // If the command type is not recognized, check if it is a defined procedure.
                    if (procedures.containsKey(cmdType)) {
                        return new Command() {
                            @Override
                            public void execute() {
                                CommandExecutor.executeProcedure(cmdType, procedures, turtle, window, logArea);
                            }
                        };
                    }
                    return null;
            }
        } catch (Exception e) {
            logArea.append("Error parsing command: " + command + "\n");
            return null;
        }
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
}
