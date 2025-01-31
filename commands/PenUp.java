/*
 * Pen Up
 * the pen is lifted from the paper
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package commands;
import entities.Turtle;

public class PenUp implements Command {
    private Turtle turtle;

    public PenUp(Turtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public void execute() {
        turtle.penUp();
    }
}
