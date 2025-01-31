/*
 * Move back command implementation 
 * It moves back the turtle without modify its orientation
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package commands;
import entities.Turtle;

public class Back implements Command {
    private Turtle turtle;
    private int distance;

    public Back(Turtle turtle, int distance) {
        this.turtle = turtle;
        this.distance = distance;
    }

    @Override
    public void execute() {
        turtle.moveBack(distance);
    }
}
