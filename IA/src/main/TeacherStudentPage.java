package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherStudentPage extends JFrame implements ActionListener {
    private Library library;
    private Student student;
    private JLabel username;
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel pages;
    private JButton deleteButton;
    private JButton returnButton;
    private JButton returnHome;

    public TeacherStudentPage(Library library, Student student) {
        super("Student Page");
        this.library = library;
        this.student = student;

        // Create input field and submit button

        username = new JLabel("Username: " + student.getUsername());

        returnHome = new JButton("Return to Teacher Home Page");
        deleteButton = new JButton("Delete Student");

        deleteButton.addActionListener(this);
        returnHome.addActionListener(this);

        JPanel contentPane = new JPanel(new GridLayout(0,1));
        contentPane.add(returnHome);
        contentPane.add(username);

        if(student.getCheckedOutBook() != null) {
            title = new JLabel("Title: " + student.getCheckedOutBook().getTitle());
            author = new JLabel("Author: " + student.getCheckedOutBook().getAuthor());
            genre = new JLabel("Genre: " + student.getCheckedOutBook().getGenre());
            pages = new JLabel("Pages: " + Integer.toString(student.getCheckedOutBook().getPages()));

            returnButton = new JButton("Return Book for Student");
            returnButton.addActionListener(this);

            contentPane.add(title);
            contentPane.add(author);
            contentPane.add(genre);
            contentPane.add(pages);
            contentPane.add(returnButton);
        }
        contentPane.add(deleteButton);
        add(contentPane, BorderLayout.NORTH);

        // Set window size and make it visible
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            boolean deleted = library.deleteStudent(student.getUsername());
            if(deleted) {
                new TeacherHomePage(library);
                dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this,"Problem deleting student!"); // Display a message dialog with the username
            }
            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        } else if(e.getSource() == returnButton) {
            boolean returned = library.returnBookTeacher(student.getCheckedOutBook());
            if(returned) {
                new TeacherHomePage(library);
                dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this,"Problem returning book!"); // Display a message dialog with the username
            }
        } else if(e.getSource() == returnHome) {
            new TeacherHomePage(library);
            dispose();
        }
    }

}
