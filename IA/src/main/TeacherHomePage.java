package main;

import javax.swing.*;

public class TeacherHomePage extends JFrame {
    private Library library;

    public TeacherHomePage(Library library) {
        super("Teacher Home Page");
        this.library = library;

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel teacherHomePanel = new JPanel();
        teacherHomePanel.setLayout(new BoxLayout(teacherHomePanel, BoxLayout.PAGE_AXIS));



        // Create input field and submit button
        JLabel label = new JLabel(library.getCurrentUser().getUsername());
        teacherHomePanel.add(label);
        tabbedPane.addTab("Home", teacherHomePanel);

        CreateBookPage createBookPage = new CreateBookPage(library);
        tabbedPane.add("Create a new book", createBookPage);

        TeacherBookSearch teacherBookSearch = new TeacherBookSearch(library);
        tabbedPane.add("Search for Books", teacherBookSearch);

        CreateStudentPage createStudentPage = new CreateStudentPage(library);
        tabbedPane.add("Create a new student", createStudentPage);

        TeacherStudentSearch teacherStudentSearch = new TeacherStudentSearch(library);
        tabbedPane.add("Search for Students", teacherStudentSearch);

        LogoutPage logoutPage = new LogoutPage(library);
        tabbedPane.add("Logout", logoutPage);


        add(tabbedPane);

        // Set window size and make it visible
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

}
