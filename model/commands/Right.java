/*
 * Right
 * models the turtle turning right by n-degrees
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package model.commands;
import model.entities.Turtle;

public class Right implements Command {
    private Turtle turtle;
    private int degrees;

    public Right(Turtle turtle, int degrees) {
        this.turtle = turtle;
        this.degrees = degrees;
    }

    @Override
    public void execute() {
        turtle.turnRight(degrees);
    }
}
