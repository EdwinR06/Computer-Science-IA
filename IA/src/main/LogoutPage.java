package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutPage extends JPanel implements ActionListener {
    private Library library;
    private JLabel logoutLabel;
    private JButton logoutButton;

    public LogoutPage(Library library) {
        this.library = library;
        setLayout(new BorderLayout());

        // Create input field and submit button
        logoutLabel = new JLabel("Are you sure you want to logout?");
        logoutButton = new JButton("Logout");

        logoutButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel();
        contentPane.add(logoutLabel);
        contentPane.add(logoutButton);
        add(contentPane, BorderLayout.NORTH);


        // Set window size and make it visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            library.logout();

            new HomePage(library);

            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        }
    }

}

