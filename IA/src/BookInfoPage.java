import javax.swing.*;
import java.awt.*;

public class BookInfoPage extends JFrame {
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel genreLabel;

    public BookInfoPage(String title, String author, String genre) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create labels for book information
        titleLabel = new JLabel("Title: " + title);
        authorLabel = new JLabel("Author: " + author);
        genreLabel = new JLabel("Genre: " + genre);

        // Add labels to content pane
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(titleLabel);
        panel.add(authorLabel);
        panel.add(genreLabel);
        add(panel);

        setVisible(true);
    }
}
