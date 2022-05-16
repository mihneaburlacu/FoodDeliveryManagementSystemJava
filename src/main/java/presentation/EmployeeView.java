package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;

public class EmployeeView extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;
    private JLabel textLabel;
    private JLabel notificationLabel;

    public EmployeeView() {
        setTitle("Employee View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 940, 615);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textArea.setBounds(10, 102, 906, 466);
        contentPane.add(textArea);

        textLabel = new JLabel("You are logged in as an employee.");
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textLabel.setBounds(704, 10, 212, 22);
        contentPane.add(textLabel);

        notificationLabel = new JLabel("Employee notification:");
        notificationLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        notificationLabel.setBounds(10, 70, 197, 22);
        contentPane.add(notificationLabel);
    }

    public void setTextAreaText(String msg) {
        textArea.setText(msg);
    }
}

