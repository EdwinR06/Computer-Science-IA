package main;

import javax.swing.*;

public class TeacherHomePage extends JFrame {
    private Library library;

    public TeacherHomePage(Library library) {
        super("Teacher Home Page");
        this.library = library;
        // make JFrame a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel teacherHomePanel = new JPanel();
        teacherHomePanel.setLayout(new BoxLayout(teacherHomePanel, BoxLayout.PAGE_AXIS));



        // Create usernmae label
        JLabel label = new JLabel("Username: " + library.getCurrentUser().getUsername());
        teacherHomePanel.add(label);

        // Add teacher tabs
        tabbedPane.addTab("Home", teacherHomePanel);

        CreateBookPage createBookPage = new CreateBookPage(library);
        tabbedPane.add("Create a new book", createBookPage);

        BookSearch bookSearch = new BookSearch(library);
        tabbedPane.add("Search for Books", bookSearch);

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
