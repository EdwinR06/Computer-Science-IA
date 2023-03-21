package main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonMouseListener extends MouseAdapter {
    private Library library;
    private JTable table;

    public ButtonMouseListener(Library library, JTable table) {
        this.library = library;
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();

        // Assuming the book information is stored in the first column of the table
        Book book = library.getBookByTitle((String) table.getValueAt(row, 0));

        new TeacherBookPage(library, book); // Redirect to the book's page
    }
}

