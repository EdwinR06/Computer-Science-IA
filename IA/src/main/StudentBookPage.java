package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentBookPage extends JFrame implements ActionListener {
    private Library library;
    private Book book;
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel pages;
    private JButton checkoutButton;
    private JButton returnButton;
    private JButton returnHome;

    public StudentBookPage(Library library, Book book) {
        super("Book Page");
        this.library = library;
        this.book = book;

        JPanel contentPane = new JPanel(new GridLayout(0, 1));

        returnHome = new JButton("Return to Home Page");
        title = new JLabel("Title: " + book.getTitle());
        author = new JLabel("Author: " + book.getAuthor());
        genre = new JLabel("Genre: " + book.getGenre());
        pages = new JLabel("Pages: " + Integer.toString(book.getPages()));



        // Add input field and submit button to content pane

        contentPane.add(returnHome);
        contentPane.add(title);
        contentPane.add(author);
        contentPane.add(genre);
        contentPane.add(pages);
        returnButton = new JButton("Return Book");
        contentPane.add(returnButton);
        checkoutButton = new JButton("Checkout Book");
        contentPane.add(checkoutButton);
        returnButton.setVisible(false);
        checkoutButton.setVisible(false);

        returnHome.addActionListener(this);
        returnButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        if(book.getIsCheckedOut() == true) {
            if(((Student) library.getCurrentUser()).getCheckedOutBook() == book) {
                returnButton.setVisible(true);
            }
        } else {
            checkoutButton.setVisible(true);
        }

        add(contentPane, BorderLayout.NORTH);

        // Set window size and make it visible
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutButton) {
            boolean checkedOut = library.checkoutBook(book);
            if(checkedOut) {
                new StudentHomePage(library);
                dispose(); // Close this window
            } else {
            JOptionPane.showMessageDialog(this, "Problem checking out book"); // Display a message dialog with the username
            }
        } else if(e.getSource() == returnButton) {
            boolean returned = library.returnBookStudent();
            if(returned) {
                new StudentHomePage(library);
                dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this, "Problem returning book");
            }
        } else if(e.getSource() == returnHome) {
            new StudentHomePage(library);
            dispose();
        }
    }

}
