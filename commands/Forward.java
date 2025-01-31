/*
 * Forward
 * moves the turtle forward
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package commands;
import entities.Turtle;

public class Forward implements Command {
    private Turtle turtle;
    private int distance;

    public Forward(Turtle turtle, int distance) {
        this.turtle = turtle;
        this.distance = distance;
    }

    @Override
    public void execute() {
        turtle.moveForward(distance);
    }
}
