package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TeacherStudentSearch extends JPanel {
    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;
    private JButton searchButton;
    private JTable searchResultsTable;
    private DefaultTableModel tableModel;
    private Library library;

    public TeacherStudentSearch(Library library) {
        setLayout(new BorderLayout());
        this.library = library;


        // Create search bar and submit button
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Add search bar and submit button to content pane
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Create table for search results
        tableModel = new DefaultTableModel(new String[]{"Username", "Current Book"}, 0);

        searchResultsTable = new JTable(tableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 0) {
                    return new ButtonRendererStudent(library);
                } else {
                    return super.getCellRenderer(row, column);
                }
            }
        };
        searchResultsTable.addMouseListener(new ButtonMouseListenerStudent(library, searchResultsTable));
        add(new JScrollPane(searchResultsTable), BorderLayout.CENTER);

        // Add action listener to submit button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchButton) {
                    // Perform search and display results
                    String searchQuery = searchField.getText();

                    // For demonstration purposes, generate some dummy search results
                    ArrayList<User> searchResults = library.getUsers();
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


                    // Display new search results
                    for (User user : searchResults) {
                        if (user.getUsername().contains(searchQuery)) {
                            if(((Student) user).getCheckedOutBook() != null){
                                tableModel.addRow(new Object[]{user.getUsername(),((Student) user).getCheckedOutBook().getTitle()});
                            } else {
                                tableModel.addRow(new Object[]{user.getUsername(),"none"});
                            }
                        }
                    }
                }
            }
        });

        setVisible(true);
    }
}
