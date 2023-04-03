package main;
import java.awt.Component;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRendererStudent extends JButton implements TableCellRenderer {
    private Library library;

    public ButtonRendererStudent(Library library) {
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
        User student = null;
        for(int i = 0; i < library.getUsers().size(); i++) {
            if(library.getUsers().get(i).getUsername().equals(value)) {
                student = library.getUsers().get(i);

            }
        }

        setText(student.getUsername());
        putClientProperty("student", student); // Set the student information as the button's client property

        return this;
    }
}
