/*
 * Repeat implementation
 * It repeates n-times a command
 * 
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
/*
 * Repeat implementation
 * It repeats n-times a command.
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package commands;

import entities.Turtle;
import view.Window;

import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

public class Repeat implements Command {
    private int times;
    private String commandStr;
    private Turtle turtle;
    private Window window;
    private Map<String, List<String>> procedures;
    private JTextArea logArea;

    public Repeat(int times, String commandStr, Turtle turtle, Window window, Map<String, List<String>> procedures, JTextArea logArea) {
        this.times = times;
        this.commandStr = commandStr;
        this.turtle = turtle;
        this.window = window;
        this.procedures = procedures;
        this.logArea = logArea;
    }

    @Override
    public void execute() {
        for (int i = 0; i < times; i++) {
            CommandExecutor.executeCommand(commandStr, turtle, window, procedures, logArea);
        }
    }
}
