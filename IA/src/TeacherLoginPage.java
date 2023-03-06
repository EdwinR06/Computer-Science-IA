import javax.swing.*;

public class TeacherLoginPage extends JFrame{
    public TeacherLoginPage(Library library) {
            super("Teacher Login");
    
            // Create label and add to content pane
            JLabel label = new JLabel("Teacher Login Page");
            JPanel contentPane = new JPanel();
            contentPane.add(label);
            setContentPane(contentPane);
    
            // Set window size and make it visible
            setSize(300, 200);
            setLocationRelativeTo(null); // Center the window on the screen
            setVisible(true);
        }
}
