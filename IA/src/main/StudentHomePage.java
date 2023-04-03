package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePage extends JFrame implements ActionListener {
    private Library library;
    private JButton returnButton;

    public StudentHomePage(Library library) {
        super("Student Home Page");
        this.library = library;

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel studentHomePanel = new JPanel();
        //studentHomePanel.setLayout(new BoxLayout(studentHomePanel, BoxLayout.PAGE_AXIS));
        studentHomePanel.setLayout(new GridLayout(0, 1));


        // Create input field and submit button
        JLabel label = new JLabel("Username: " + library.getCurrentUser().getUsername());
        studentHomePanel.add(label);

        if(((Student) library.getCurrentUser()).getCheckedOutBook() != null) {
            JLabel checkedOut = new JLabel("Current Book");
            JLabel labelTitle = new JLabel("Title: " + ((Student) library.getCurrentUser()).getCheckedOutBook().getTitle());
            JLabel labelAuthor = new JLabel("Author: " + ((Student) library.getCurrentUser()).getCheckedOutBook().getAuthor());
            JLabel labelGenre = new JLabel("Genre: " + ((Student) library.getCurrentUser()).getCheckedOutBook().getGenre());
            JLabel labelPages = new JLabel("Number of pages: " + String.valueOf(((Student) library.getCurrentUser()).getCheckedOutBook().getGenre()));

            studentHomePanel.add(checkedOut);
            studentHomePanel.add(labelTitle);
            studentHomePanel.add(labelAuthor);
            studentHomePanel.add(labelGenre);
            studentHomePanel.add(labelPages);

            returnButton = new JButton("Return Book");

            studentHomePanel.add(returnButton);
            returnButton.addActionListener(this);

        }


        tabbedPane.addTab("Home", studentHomePanel);

        StudentBookSearch studentBookSearch = new StudentBookSearch(library);
        tabbedPane.add("Search for Books", studentBookSearch);

        LogoutPage logoutPage = new LogoutPage(library);
        tabbedPane.add("Logout", logoutPage);


        add(tabbedPane);

        // Set window size and make it visible
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnButton) {
            boolean returned = library.returnBookStudent();
            if(returned) {
                dispose(); // Close this window
                new StudentHomePage(library);
            } else {
                JOptionPane.showMessageDialog(this, "Problem returning book");
            }
        }
    }

}
