package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

/*
 * ProcedureManager manages the state for procedure definition.
 * It encapsulates the procedure definition state, such as whether we are defining a procedure,
 * the current procedure's name, and the list of commands in the current procedure.
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
public class ProcedureManager {
    private boolean definingProcedure = false;
    private String currentProcedureName = "";
    private List<String> currentProcedureCommands = new ArrayList<>();

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

    public void finishProcedure(Map<String, List<String>> procedures, JTextArea logArea) {
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
}
