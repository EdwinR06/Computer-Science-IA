package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.System.exit;

public class HomePage extends JFrame implements ActionListener {

    private JButton exitButton;
    private JButton teacherLoginButton;
    private JButton studentLoginButton;
    private Library library;
    private String bookDir;
    private String teacherDir;
    private String usersDir;
    public HomePage(Library library) {
        super("Classroom Library System");
        this.library = library;
        this.bookDir = library.getDirs()[0];
        this.teacherDir = library.getDirs()[1];
        this.usersDir = library.getDirs()[2];

        // Create buttons
        exitButton = new JButton("Exit");
        teacherLoginButton = new JButton("Teacher");
        studentLoginButton = new JButton("Student");

        // Add action listeners to buttons
        exitButton.addActionListener(this);
        teacherLoginButton.addActionListener(this);
        studentLoginButton.addActionListener(this);

        // Add buttons to content pane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(exitButton);
        contentPane.add(teacherLoginButton);
        contentPane.add(studentLoginButton);
        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            // Exit the program
            try {
                library.saveBooks(bookDir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                library.saveTeacher(teacherDir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                library.saveUsers(usersDir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        } else if (e.getSource() == teacherLoginButton) {
            // Go to teacher login window
            new TeacherLoginPage(library);
            dispose(); // Close this window
        } else if (e.getSource() == studentLoginButton) {
            // Go to student login window
            new StudentLoginPage(library);
            dispose(); // Close this window
        }
    }
}