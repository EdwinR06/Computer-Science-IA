import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StudentBookSearchResults extends JFrame implements ActionListener {
    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;
    private JButton searchButton;
    private JTable searchResultsTable;
    private DefaultTableModel tableModel;

    public StudentBookSearchResults() {
        super("Search Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create search bar and submit button
        searchField = new JTextField(20);
        searchTypeComboBox = new JComboBox<String>(new String[]{"Title", "Author", "Genre"});
        searchButton = new JButton("Search");

        // Add search bar and submit button to content pane
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchTypeComboBox);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Create table for search results
        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Genre"}, 0);
        searchResultsTable = new JTable(tableModel);
        add(new JScrollPane(searchResultsTable), BorderLayout.CENTER);

        // Add action listener to submit button
        searchButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // Perform search and display results
            String searchQuery = searchField.getText();
            String searchType = (String) searchTypeComboBox.getSelectedItem();

            // For demonstration purposes, generate some dummy search results
            List<Book> searchResults = new ArrayList<Book>();
            searchResults.add(new Book("To Kill a Mockingbird", "Harper Lee", "A classic novel about racism and injustice in the American South.", 40));
            searchResults.add(new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age novel about teenage angst and rebellion.", 30));
            searchResults.add(new Book("1984", "George Orwell", "A dystopian novel about totalitarianism and thought control.", 40));

            // Clear old search results
            tableModel.setRowCount(0);


            // Set the column model to use a non-editable cell renderer
            searchResultsTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            });
            searchResultsTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            });
            searchResultsTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            });

            // Display new search results
            for (Book book : searchResults) {
                if (searchType.equals("Title") && book.getTitle().contains(searchQuery)) {
                    tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                } else if (searchType.equals("Author") && book.getAuthor().contains(searchQuery)) {
                    tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                } else if (searchType.equals("Keyword") && book.getGenre().contains(searchQuery)) {
                    tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                }
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentBookSearchResults();
            }
        });
    }


}