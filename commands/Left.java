/*
 * Left
 * models the turtle turning left by n-degrees
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package commands;
import entities.Turtle;

public class Left implements Command {
    private Turtle turtle;
    private int degrees;

    public Left(Turtle turtle, int degrees) {
        this.turtle = turtle;
        this.degrees = degrees;
    }

    @Override
    public void execute() {
        turtle.turnLeft(degrees);
    }
}
