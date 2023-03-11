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

        JPanel contentPane = new JPanel(new FlowLayout());

        returnHome = new JButton("Return to Home Page");
        title = new JLabel(book.getTitle());
        author = new JLabel(book.getAuthor());
        genre = new JLabel(book.getGenre());
        pages = new JLabel(Integer.toString(book.getPages()));


        returnHome.addActionListener(this);
        returnButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        // Add input field and submit button to content pane

        contentPane.add(returnHome);
        contentPane.add(title);
        contentPane.add(author);
        contentPane.add(genre);
        contentPane.add(pages);
        if(book.getIsCheckedOut() == true) {
            if(((Student) library.getCurrentUser()).getCheckedOutBook() == book) {
                returnButton = new JButton("Return Book");
                contentPane.add(returnButton);
            }
        } else {
            checkoutButton = new JButton("Checkout Book");
            contentPane.add(checkoutButton);
        }

        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
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
