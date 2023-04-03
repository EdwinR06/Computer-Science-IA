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

        // Add input fields, labels, and submit button
        JPanel contentPane = new JPanel(new GridLayout(0, 1));
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



        // Set visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String title = inputFieldTitle.getText(); // Get the title entered in the input field
            String author = inputFieldAuthor.getText(); // Get the author entered in the input field
            String genre = inputFieldGenre.getText(); // Get the genre entered in the input field
            String pages = inputFieldPages.getText(); // Get the number of pages entered in the input field

            try {
                int i = Integer.parseInt(pages);
                // Check for empty input fields
                if(!title.equals("") && !author.equals("") && !genre.equals("") && !pages.equals("")) {
                    boolean created = library.addBook(new Book(title, author, genre,Integer.parseInt(pages)));
                    if(created) {
                        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                        parentFrame.dispose();

                        // redirect back to teach home page
                        new TeacherHomePage(library);
                    } else {
                        // error for existing book or full library
                        JOptionPane.showMessageDialog(this, "Please enter a new book."); // Display a message dialog with book message
                    }
                } else {
                    // empty fields
                    JOptionPane.showMessageDialog(this, "Please fill all fields"); // Display a message dialog with book message
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter an integer to number of pages field"); // Display a message dialog with int message
            }


        }
    }

}
