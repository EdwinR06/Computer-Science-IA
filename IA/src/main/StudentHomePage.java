package main;

import javax.swing.*;

public class StudentHomePage extends JFrame {
    private Library library;

    public StudentHomePage(Library library) {
        super("Student Home Page");
        this.library = library;

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel studentHomePanel = new JPanel();
        studentHomePanel.setLayout(new BoxLayout(studentHomePanel, BoxLayout.PAGE_AXIS));



        // Create input field and submit button
        JLabel label = new JLabel(library.getCurrentUser().getUsername());
        studentHomePanel.add(label);
        tabbedPane.addTab("Home", studentHomePanel);

        StudentBookSearch studentBookSearch = new StudentBookSearch(library);
        tabbedPane.add("Search for Books", studentBookSearch);


        add(tabbedPane);

        // Set window size and make it visible
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

}
