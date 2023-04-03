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

        // Create label and logout
        logoutLabel = new JLabel("Are you sure you want to logout?");
        logoutButton = new JButton("Logout");

        logoutButton.addActionListener(this);

        // Add label and logout button
        JPanel contentPane = new JPanel();
        contentPane.add(logoutLabel);
        contentPane.add(logoutButton);
        add(contentPane, BorderLayout.NORTH);


        // Setvisible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            library.logout(); // logout current user
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose(); // close window
            // redirect to home page
            new HomePage(library);
        }
    }

}

