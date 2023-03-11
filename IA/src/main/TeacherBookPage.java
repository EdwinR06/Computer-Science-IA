package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherBookPage extends JFrame implements ActionListener {
    private Library library;
    private Book book;
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel pages;
    private JButton deleteButton;
    private JButton returnButton;
    private JButton returnHome;

    public TeacherBookPage(Library library, Book book) {
        super("Book Page");
        this.library = library;
        this.book = book;

        // Create input field and submit button

        title = new JLabel(book.getTitle());
        author = new JLabel(book.getAuthor());
        genre = new JLabel(book.getGenre());
        pages = new JLabel(Integer.toString(book.getPages()));

        returnHome = new JButton("Return to Teacher Home Page");
        returnButton = new JButton("Return Book for Student");
        deleteButton = new JButton("Delete Book");


        returnButton.addActionListener(this);
        deleteButton.addActionListener(this);
        returnHome.addActionListener(this);

        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(returnHome);
        contentPane.add(title);
        contentPane.add(author);
        contentPane.add(genre);
        contentPane.add(pages);
        contentPane.add(deleteButton);
        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            boolean deleted = library.deleteBook(book.getTitle());
            if(deleted) {
                new TeacherHomePage(library);
                dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this,"Problem deleting book!"); // Display a message dialog with the username
            }
            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        } else if(e.getSource() == returnButton) {
            boolean returned = library.returnBookTeacher(book);
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
