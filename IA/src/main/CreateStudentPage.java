package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStudentPage extends JPanel implements ActionListener {
    private Library library;
    private JTextField inputField;
    private JButton submitButton;

    public CreateStudentPage(Library library) {
        this.library = library;
        setLayout(new BorderLayout());

        // Create input field and submit button
        inputField = new JTextField(20);
        submitButton = new JButton("Create");

        submitButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel();
        contentPane.add(inputField);
        contentPane.add(submitButton);
        add(contentPane, BorderLayout.NORTH);


        // Set window size and make it visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = inputField.getText(); // Get the username entered in the input field
            boolean created = library.createStudent(username);
            if(created) {
                new TeacherHomePage(library);
                //dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this, "Please enter new username!"); // Display a message dialog with the username

            }


            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        }
    }

}
