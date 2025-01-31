/*
 * Repeat implementation
 * It repeates n-times a command
 * 
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package commands;

public class Repeat implements Command {
    private int times;
    private Command command;

    public Repeat(int times, Command command) {
        this.times = times;
        this.command = command;
    }

    @Override
    public void execute() {
        for (int i = 0; i < times; i++) {
            command.execute();
        }
    }
}
