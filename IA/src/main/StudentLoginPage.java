package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginPage extends JFrame implements ActionListener {
    private Library library;
    private JTextField inputField;
    private JButton submitButton;

    private JButton returnHome;
    private JLabel usernameLabel;

    public StudentLoginPage(Library library) {
        super("Student Login Page");
        this.library = library;

        // Create input field and submit button
        usernameLabel = new JLabel("Username: ");
        inputField = new JTextField(20);
        submitButton = new JButton("Login");
        returnHome = new JButton("Return to Home Page");

        returnHome.addActionListener(this);
        submitButton.addActionListener(this);


        // Add input field, label, and buttons

        JPanel contentPane = new JPanel(new GridLayout(0, 1));
        contentPane.add(returnHome);
        contentPane.add(usernameLabel);
        contentPane.add(inputField);
        contentPane.add(submitButton);
        add(contentPane, BorderLayout.NORTH);


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
                dispose(); // Close this window
                new StudentHomePage(library);

            } else {
               JOptionPane.showMessageDialog(this, "Please enter valid username!"); // Display a message dialog with username error
            }
        } else if(e.getSource() == returnHome) {
            dispose();
            new HomePage(library);
        }
    }

}
