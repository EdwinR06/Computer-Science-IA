package main;
import java.awt.Component;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    private Library library;

    public ButtonRenderer(Library library) {
        this.library = library;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
        }
        Book book = library.getBookByTitle(value.toString()); // Assuming the value is a Book object
        setText(book.getTitle()); // Set the button text to the book title
        putClientProperty("book", book); // Set the book information as the button's client property

        return this;
    }
}
