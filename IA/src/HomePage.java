import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    private JButton exitButton;
    private JButton teacherLoginButton;
    private JButton studentLoginButton;
    private Library library;

    public HomePage(Library library) {
        super("Classroom Library System");
        this.library = library;
        // Create buttons
        exitButton = new JButton("Exit");
        teacherLoginButton = new JButton("Teacher");
        studentLoginButton = new JButton("Student");

        // Add action listeners to buttons
        exitButton.addActionListener(this);
        teacherLoginButton.addActionListener(this);
        studentLoginButton.addActionListener(this);

        // Add buttons to content pane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(exitButton);
        contentPane.add(teacherLoginButton);
        contentPane.add(studentLoginButton);
        setContentPane(contentPane);

        // Set window size and make it visible
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            // Exit the program
            System.exit(0);
        } else if (e.getSource() == teacherLoginButton) {
            // Go to teacher login window
            new TeacherLoginPage(library);
            dispose(); // Close this window
        } else if (e.getSource() == studentLoginButton) {
            // Go to student login window
            new StudentLoginPage(library);
            dispose(); // Close this window
        }
    }
}