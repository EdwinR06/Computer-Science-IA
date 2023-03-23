package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginPage extends JFrame implements ActionListener {
    private Library library;
    private JTextField inputField;
    private JButton submitButton;

    public StudentLoginPage(Library library) {
        super("Student Login Page");
        this.library = library;

        // Create input field and submit button
        inputField = new JTextField(20);
        submitButton = new JButton("Login");

        submitButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(inputField);
        contentPane.add(submitButton);
        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = inputField.getText(); // Get the username entered in the input field
            boolean loggedIn = library.loginStudent(username);
            if(loggedIn) {
                new StudentHomePage(library);
                dispose(); // Close this window
            } else {
               JOptionPane.showMessageDialog(this, "Please enter valid username!"); // Display a message dialog with the username

            }
            

            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        } 
    }

}
