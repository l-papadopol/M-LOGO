/*
 * Pen Down
 * the pen now writes on the paper
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package model.commands;
import model.entities.Turtle;

public class PenDown implements Command {
    private Turtle turtle;

    public PenDown(Turtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public void execute() {
        turtle.penDown();
    }
}
