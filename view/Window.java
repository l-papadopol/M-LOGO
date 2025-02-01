package view;

import entities.*;
import javax.swing.JPanel;
import java.awt.*;

public class Window extends JPanel {
    private Turtle turtle;
    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public Window(Turtle turtle) {
        this.turtle = turtle;
        setPreferredSize(new Dimension(800, 800));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw all the lines traced by the turtle
        for (Line line : turtle.getTraces()) {
            g2d.setColor(line.getColor()); 
            g2d.setStroke(new BasicStroke(line.getThickness()));
            g2d.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }

        // Draw the turtle
        drawTurtle(g2d, turtle.getX(), turtle.getY(), turtle.getAngle());
    }

    // Method to draw the turtle as a triangle
    private void drawTurtle(Graphics2D g2d, int x, int y, int angle) {
        int size = 30;  // Size of the triangle (base and height)
        int halfBase = size / 2;

        // Calculate the coordinates of the three vertices of the triangle
        int x1 = x + (int) (size * Math.cos(Math.toRadians(angle)));
        int y1 = y + (int) (size * Math.sin(Math.toRadians(angle)));

        int x2 = x + (int) (halfBase * Math.cos(Math.toRadians(angle + 120)));
        int y2 = y + (int) (halfBase * Math.sin(Math.toRadians(angle + 120)));

        int x3 = x + (int) (halfBase * Math.cos(Math.toRadians(angle - 120)));
        int y3 = y + (int) (halfBase * Math.sin(Math.toRadians(angle - 120)));

        // Create the triangle
        Polygon turtleShape = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        g2d.setColor(turtle.getPenColor()); 
        g2d.fill(turtleShape);
    }

    // update the window
    public void update() {
        repaint(); 
    }
}
