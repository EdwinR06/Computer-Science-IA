package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonMouseListenerBook extends MouseAdapter {
    private Library library;
    private JTable table;
    private Component component;

    public ButtonMouseListenerBook(Library library, JTable table, Component component) {
        this.library = library;
        this.table = table;
        this.component = component;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();

        // Book title is stored in first column
        Book book = library.getBookByTitle((String) table.getValueAt(row, 0));
        if(library.getCurrentUser() instanceof Teacher) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(component);
            parentFrame.dispose();
            new TeacherBookPage(library, book); // Redirect to the book's page
        } else if (library.getCurrentUser() instanceof Student) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(component);
            parentFrame.dispose();
            new StudentBookPage(library, book);
        }

    }
}

