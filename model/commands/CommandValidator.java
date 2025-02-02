/*
 * CommandValidator validates commant
 * returns true or false 
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package model.commands;

import java.util.Map;
import java.util.List;

public class CommandValidator {

    // Method that validates the entered commands
    public static boolean validateCommand(String command, Map<String, List<String>> procedures) {
        // Remove extra spaces before and after the command
        command = command.trim();

        // If the command is empty, it is invalid
        if (command.isEmpty()) {
            return false;  // Empty command
        }
        
        // Split the command based on spaces
        String[] parts = command.split("\\s+");
        
        // Verify that there is at least one command
        if (parts.length < 1) {
            return false;  // Empty command
        }
        
        switch (parts[0]) {
            case "FORWARD":
            case "BACK":
                // These commands must be followed by a distance (a number)
                if (parts.length != 2) {
                    return false;  // Distance is missing
                }
                try {
                    Integer.parseInt(parts[1]);  // Try to convert the distance into a number
                } catch (NumberFormatException e) {
                    return false;  // Distance must be a number
                }
                break;
                
            case "RIGHT":
            case "LEFT":
                // These commands must be followed by an angle
                if (parts.length != 2) {
                    return false;  // Angle is missing
                }
                try {
                    Integer.parseInt(parts[1]);  // Try to convert the angle into a number
                } catch (NumberFormatException e) {
                    return false;  // Angle must be a number
                }
                break;
                
            case "THICKNESS":
                // This command must be followed by a thickness
                if (parts.length != 2) {
                    return false;  // Thickness is missing
                }
                try {
                    Integer.parseInt(parts[1]);  // Try to convert the thickness into a number
                } catch (NumberFormatException e) {
                    return false;  // Thickness must be a number
                }
                break;
                
            case "SETCOLOR":
                // This command must be followed by a color name
                if (parts.length != 2) {
                    return false;  // Color is missing
                }
                // Verify that the color is valid
                String colorName = parts[1].toUpperCase();  // Convert the color for comparison
                if (!colorName.equals("RED") && !colorName.equals("GREEN") && !colorName.equals("BLUE") && !colorName.equals("BLACK")) {
                    return false;  // Invalid color
                }
                break;
                
            case "PENUP":
            case "PENDOWN":
                break;
            
            case "REPEAT":
                if (parts.length < 3 || !parts[2].startsWith("[")) {
                    return false; // Repeat command must be followed by a valid block
                }
                try {
                    Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    return false; // Repeat count must be a number
                }
                String repeatCommand = command.substring(command.indexOf("[") + 1, command.lastIndexOf("]")).trim();
                if (!validateCommand(repeatCommand, procedures) && !procedures.containsKey(repeatCommand)) {
                    return false; // Ensure repeat block contains a valid command or procedure
                }
                break;
                
            default:
                return false;  // Invalid command, not recognized
        }
        
        // If all checks pass, the command is valid
        return true;
    }
}

