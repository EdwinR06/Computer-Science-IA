package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherLoginPage extends JFrame implements ActionListener {

    private Library library;
    private JLabel usernameLabel;
    private JTextField usernameInputField;
    private JLabel passwordLabel;
    private JTextField passwordInputField;
    private JButton submitButton;

    private JButton returnHome;


    public TeacherLoginPage(Library library) {
        super("Teacher Login Page");
        this.library = library;

        // Create input field and submit button
        returnHome = new JButton("Return to Home Page");
        usernameLabel = new JLabel("Username:");
        usernameInputField = new JTextField(20);
        passwordLabel = new JLabel("Password: ");
        passwordInputField = new JTextField(20);
        submitButton = new JButton("Login");

        returnHome.addActionListener(this);
        submitButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel(new GridLayout(0, 1));

        contentPane.add(returnHome);
        contentPane.add(usernameLabel);
        contentPane.add(usernameInputField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordInputField);
        contentPane.add(submitButton);
        add(contentPane, BorderLayout.NORTH);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = usernameInputField.getText(); // Get the username entered in the input field
            String password = passwordInputField.getText();
            boolean loggedIn = library.loginTeacher(username, password);
            if(loggedIn) {
                dispose(); // Close this window
                new TeacherHomePage(library);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter valid username and password!"); // Display a message dialog with the username

            }


            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        } else if(e.getSource() == returnHome) {
            dispose();
            new HomePage(library);
        }
    }
}
