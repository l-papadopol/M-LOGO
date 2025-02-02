package model.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

/*
 * ProcedureManager manages the state for procedure definition.
 * It encapsulates the procedure definition state (e.g., whether we are defining a procedure,
 * the current procedure's name, and the list of commands in the current procedure)
 * and now also stores all defined procedures.
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
public class ProcedureManager {
    private boolean definingProcedure = false;
    private String currentProcedureName = "";
    private List<String> currentProcedureCommands = new ArrayList<>();
    
    // Entire procedure storage is now encapsulated here.
    private Map<String, List<String>> procedures = new HashMap<>();
    
    public boolean isDefiningProcedure() {
        return definingProcedure;
    }
    
    public String getCurrentProcedureName() {
        return currentProcedureName;
    }
    
    public void startProcedure(String name, JTextArea logArea) {
        definingProcedure = true;
        currentProcedureName = name;
        currentProcedureCommands.clear();
        logArea.append("Procedure definition '" + name + "' started.\n");
        logArea.append("Enter commands, type 'END' to finish.\n");
    }
    
    public void addCommand(String command, JTextArea logArea) {
        currentProcedureCommands.add(command);
        logArea.append("Added to procedure: " + command + "\n");
    }
    
    // Finish the current procedure by storing it in the internal map.
    public void finishProcedure(JTextArea logArea) {
        if (currentProcedureCommands.isEmpty()) {
            logArea.append("Error: the procedure '" + currentProcedureName + "' is empty and was not saved.\n");
        } else {
            procedures.put(currentProcedureName, new ArrayList<>(currentProcedureCommands));
            logArea.append("Procedure '" + currentProcedureName + "' successfully defined.\n");
        }
        definingProcedure = false;
        currentProcedureName = "";
        currentProcedureCommands.clear();
    }
    
    // Helper methods to interact with stored procedures.
    public boolean containsProcedure(String name) {
        return procedures.containsKey(name);
    }
    
    public List<String> getProcedure(String name) {
        return procedures.get(name);
    }
    
    // If needed by other parts of the code, you can expose the whole map.
    public Map<String, List<String>> getProcedures() {
        return procedures;
    }
}
