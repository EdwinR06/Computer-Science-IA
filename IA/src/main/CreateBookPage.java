package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBookPage extends JPanel implements ActionListener {
    private Library library;
    private JLabel titleLabel;
    private JTextField inputFieldTitle;
    private JLabel authorLabel;
    private JTextField inputFieldAuthor;
    private JLabel genreLabel;
    private JTextField inputFieldGenre;
    private JLabel pagesLabel;
    private JTextField inputFieldPages;
    private JButton submitButton;

    public CreateBookPage(Library library) {
        this.library = library;
        setLayout(new BorderLayout());

        // Create input field and submit button
        titleLabel = new JLabel("Enter title:");
        inputFieldTitle = new JTextField(50);
        authorLabel = new JLabel("Enter author:");
        inputFieldAuthor = new JTextField(50);
        genreLabel = new JLabel("Enter genre:");
        inputFieldGenre = new JTextField(50);
        pagesLabel = new JLabel("Enter the number of pages:");
        inputFieldPages = new JTextField(10);
        submitButton = new JButton("Create");

        submitButton.addActionListener(this);

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel();
        contentPane.add(titleLabel);
        contentPane.add(inputFieldTitle);
        contentPane.add(authorLabel);
        contentPane.add(inputFieldAuthor);
        contentPane.add(genreLabel);
        contentPane.add(inputFieldGenre);
        contentPane.add(pagesLabel);
        contentPane.add(inputFieldPages);
        contentPane.add(submitButton);
        add(contentPane, BorderLayout.NORTH);


        // Set window size and make it visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String title = inputFieldTitle.getText(); // Get the username entered in the input field
            String author = inputFieldAuthor.getText();
            String genre = inputFieldGenre.getText();
            String pages = inputFieldPages.getText();

            boolean created = library.addBook(new Book(title, author, genre,Integer.parseInt(pages)));
            if(created) {
                new TeacherHomePage(library);
                //dispose(); // Close this window
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a new book."); // Display a message dialog with the username

            }


            //JOptionPane.showMessageDialog(this, "Hello " + username + "!"); // Display a message dialog with the username
        }
    }

}
