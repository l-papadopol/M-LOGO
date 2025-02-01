package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * LogoView is responsible for the presentation layer of the M-LOGO application.
 * It creates the main window including the drawing area, log area, and command input panel.
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
public class LogoView {
    private JFrame frame;
    private JTextArea logArea;
    private JTextField inputField;
    private JButton sendButton;
    // Use your existing drawing component (Window) which is now in the view package.
    private Window drawingArea;

    public LogoView(Window drawingArea) {
        this.drawingArea = drawingArea;
        // Create and configure the main frame
        frame = new JFrame("M-LOGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the log area and add it to a scroll pane at the top
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.NORTH);

        // Add the drawing area (your Window)
        frame.add(drawingArea, BorderLayout.CENTER);

        // Create the command input panel at the bottom
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputField = new JTextField(50);
        sendButton = new JButton("Send Command");
        commandPanel.add(inputField);
        commandPanel.add(sendButton);
        frame.add(commandPanel, BorderLayout.SOUTH);

        frame.setSize(800, 900);
    }

    /**
     * Displays the main window.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Sets an ActionListener for the send button and input field.
     *
     * @param listener The ActionListener to attach.
     */
    public void setSendButtonListener(ActionListener listener) {
        sendButton.addActionListener(listener);
        inputField.addActionListener(listener);
    }

    /**
     * Returns the current text entered in the input field.
     *
     * @return the command input text.
     */
    public String getCommandInput() {
        return inputField.getText().trim();
    }

    /**
     * Clears the command input field.
     */
    public void clearCommandInput() {
        inputField.setText("");
    }
    
    /**
     * Returns the drawing area component.
     *
     * @return the drawing area (Window component).
     */
    public Window getDrawingArea() {
        return drawingArea;
    }


    /**
     * Appends a message to the log area.
     *
     * @param message The message to append.
     */
    public void appendLog(String message) {
        logArea.append(message);
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}
