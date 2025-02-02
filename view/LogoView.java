/*
 * LOGO view
 * accordingly to MVC design pattern, this is the class that creates the view
 *(C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */

package view;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.ActionListener;
import model.entities.Turtle;

public class LogoView {
    private JFrame frame;
    private JTextArea logArea;
    private JTextField inputField;
    private JButton sendButton;
    private DrawingArea drawingWindow;

    public LogoView(Turtle turtle) {
        // Create the drawing area
        drawingWindow = new DrawingArea(turtle);

        // Create and configure the main frame
        frame = new JFrame("M-LOGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(drawingWindow, BorderLayout.CENTER);

        // Create the input panel (log area, command field, and button)
        JPanel inputPanel = createInputPanel();
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setSize(800, 900);
    }

    // I really don't know how I've made this... I've just tried till the GUI appeared looking "almost good"
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        
        // Set auto-scrolling behavior by updating the caret policy
        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(logArea);
        inputPanel.add(scrollPane, BorderLayout.NORTH);
        

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(commandPanel, BorderLayout.CENTER);

        inputField = new JTextField(60);
        commandPanel.add(inputField);
        sendButton = new JButton("Execute");
        commandPanel.add(sendButton);

        logArea.append("Welcome! Here are the available commands:\n");
        showMenu();
        return inputPanel;
    }

    // Show the frame
    public void show() {
        frame.setVisible(true);
    }

    // Allow the controller to register a listener for command input (both field and button)
    public void addCommandListener(ActionListener listener) {
        sendButton.addActionListener(listener);
        inputField.addActionListener(listener);
    }

    // Return the text from the input field
    public String getCommand() {
        return inputField.getText();
    }

    // Clear the input field
    public void clearCommandInput() {
        inputField.setText("");
    }

    // Append a message to the log area
    public void log(String message) {
        logArea.append(message);
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // Request an update of the drawing area
    public void updateDrawing() {
        drawingWindow.update();
    }

    // Getter for the drawing component (DrawingArea)
    public DrawingArea getDrawingWindow() {
        return drawingWindow;
    }
    
    // Getter for the log area
    public JTextArea getLogArea() {
        return logArea;
    }
    
    private void showMenu() {
        logArea.append("Available commands:\n");
        logArea.append("FORWARD <distance> - Moves the turtle forward\n");
        logArea.append("BACK <distance> - Moves the turtle backward\n");
        logArea.append("RIGHT <degrees> - Rotates the turtle to the right\n");
        logArea.append("LEFT <degrees> - Rotates the turtle to the left\n");
        logArea.append("PENUP - Lifts the pen\n");
        logArea.append("PENDOWN - Lowers the pen\n");
        logArea.append("THICKNESS <thickness> - Sets the line thickness\n");
        logArea.append("SETCOLOR <color> - Changes the pen color (e.g., RED, BLUE, GREEN)\n");
        logArea.append("TO <name> - Starts defining a procedure\n");
        logArea.append("END - Finishes defining a procedure\n");
        logArea.append("EXIT - Exits the program\n");
    }
}
