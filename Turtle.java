/*
 * Turtle
 * model of a triangular turtle
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package entities;
import java.awt.Color;
import java.util.ArrayList;

public class Turtle {
    private int x, y;
    private int angle;
    private boolean penDown;
    private Color penColor;
    private int thickness;
    private ArrayList<Line> traces;

    public Turtle(int x, int y) {
        this.x = x;
        this.y = y;
        this.angle = -90; // Pointing upwards
        this.penDown = true; // Pen is down by default
        this.penColor = Color.BLACK; // Default pen color
        this.thickness = 1; // Single pixel default thickness
        this.traces = new ArrayList<>();
    }

    // Methods to get position and angle
    public int getX() { 
        return this.x; 
    }

    public int getY() { 
        return this.y; 
    }

    public int getAngle() { 
        return this.angle; 
    }

    // Method to get the pen color
    public Color getPenColor() { return penColor; }

    // Methods to modify position and angle
    public void moveForward(int distance) {
        int newX = x + (int) (distance * Math.cos(Math.toRadians(angle)));
        int newY = y + (int) (distance * Math.sin(Math.toRadians(angle)));

        if (penDown) {
            traces.add(new Line(x, y, newX, newY, penColor, thickness));  // Adds the traced line
        }

        this.x = newX;
        this.y = newY;
    }

    // Moving backward is equivalent to moving forward with a negative distance
    // Avoids rotating 180° and saves a command
    public void moveBack(int distance) {
        moveForward(-distance);
    }

    // Rotates to the right
    public void turnRight(int degrees) {
        angle += degrees;  
    }

    // Rotates to the left
    public void turnLeft(int degrees) {
        angle -= degrees;  
    }

    // The pen is lifted
    public void penUp() {
        penDown = false;  
    }

    // The pen is lowered
    public void penDown() {
        penDown = true;  
    }

    // Sets the pen color
    public void setPenColor(Color color) {
        penColor = color;  
    }

    // Sets the line thickness
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    // Returns all traced lines
    public ArrayList<Line> getTraces() {
        return this.traces;  
    }
}
