package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StudentBookSearch extends JPanel {
    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;
    private JButton searchButton;
    private JTable searchResultsTable;
    private DefaultTableModel tableModel;
    private Library library;

    public StudentBookSearch(Library library) {
        setLayout(new BorderLayout());
        this.library = library;


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
        searchResultsTable = new JTable(tableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 0) {
                    return new ButtonRenderer(library);
                } else {
                    return super.getCellRenderer(row, column);
                }
            }
        };
        searchResultsTable.addMouseListener(new ButtonMouseListener(library, searchResultsTable));
        add(new JScrollPane(searchResultsTable), BorderLayout.CENTER);

        // Add action listener to submit button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchButton) {
                    // Perform search and display results
                    String searchQuery = searchField.getText();
                    String searchType = (String) searchTypeComboBox.getSelectedItem();

                    // For demonstration purposes, generate some dummy search results
                    Book[] searchResults = library.getBooks();
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
                        if (book != null && searchType.equals("Title") && book.getTitle().contains(searchQuery)) {
                            tableModel.addRow(new String[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                        } else if (book != null && searchType.equals("Author") && book.getAuthor().contains(searchQuery)) {
                            tableModel.addRow(new String[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                        } else if (book != null && searchType.equals("Genre") && book.getGenre().contains(searchQuery)) {
                            tableModel.addRow(new String[]{book.getTitle(), book.getAuthor(), book.getGenre()});
                        }
                    }
                }
            }
        });

        setVisible(true);
    }
}