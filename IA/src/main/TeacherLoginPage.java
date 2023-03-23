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



    public TeacherLoginPage(Library library) {
        super("Teacher Login Page");
        this.library = library;

        // Create input field and submit button
        usernameLabel = new JLabel("Username:");
        usernameInputField = new JTextField(20);
        passwordLabel = new JLabel("password");
        passwordInputField = new JTextField(20);
        submitButton = new JButton("Login");

        submitButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(usernameLabel);
        contentPane.add(usernameInputField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordInputField);
        contentPane.add(submitButton);
        setContentPane(contentPane);

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
                new TeacherHomePage(library);
                dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this, "Please enter valid username and password!"); // Display a message dialog with the username

            }


            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        }
    }
}
