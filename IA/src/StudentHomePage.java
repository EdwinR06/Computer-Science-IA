import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePage extends JFrame {
    private Library library;

    public StudentHomePage(Library library) {
        super("Student Home Page");
        this.library = library;

        // Create input field and submit button
        JLabel label = new JLabel(library.getCurrentUser().getUsername());
        JButton searchBooksButton = new JButton("Search for Books");

        searchBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Go to student login window
                new BookSearchPage(library);
                 // Close this window
                 dispose();
            }
        });

        // Add input field and submit button to content pane
        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(label);
        contentPane.add(searchBooksButton);
        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

}
