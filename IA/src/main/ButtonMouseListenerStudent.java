package main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonMouseListenerStudent extends MouseAdapter {
    private Library library;
    private JTable table;

    public ButtonMouseListenerStudent(Library library, JTable table) {
        this.library = library;
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();


        Student student = null;
        for(int i = 0; i < library.getUsers().size(); i++) {
            if(library.getUsers().get(i).getUsername().equals(table.getValueAt(row, 0))) {
                student = (Student) library.getUsers().get(i);

            }
        }

        // Book title is stored in first column
        if(library.getCurrentUser() instanceof Teacher) {
            new TeacherStudentPage(library, student); // Redirect to the book's page
        }

    }
}

