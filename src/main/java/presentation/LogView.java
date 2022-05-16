package presentation;

import enums.Role;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

import static javax.swing.JOptionPane.showMessageDialog;

public class LogView extends JFrame {

    private JPanel contentPane;
    private JLabel registerLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField usernameTextField;
    private JLabel roleLabel;
    private JComboBox roleComboBox;
    private JButton registerButton;
    private JTextField usernameLogInTextField;
    private JTextField passwordLogInTextField;
    private JLabel usernameLogInLabel;
    private JLabel passwordLogInLabel;
    private JButton loginButton;
    private JLabel loginLabel;
    private JLabel registerTextLabel;

    public LogView() {
        setTitle("FOOD DELIVERY MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        registerLabel = new JLabel("Register:");
        registerLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        registerLabel.setBounds(30, 37, 120, 30);
        contentPane.add(registerLabel);

        usernameLabel = new JLabel("username:");
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        usernameLabel.setBounds(30, 90, 65, 13);
        contentPane.add(usernameLabel);

        passwordLabel = new JLabel("password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordLabel.setBounds(30, 125, 65, 13);
        contentPane.add(passwordLabel);

        emailLabel = new JLabel("email:");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        emailLabel.setBounds(30, 160, 65, 13);
        contentPane.add(emailLabel);

        phoneLabel = new JLabel("phone number:");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        phoneLabel.setBounds(30, 195, 86, 13);
        contentPane.add(phoneLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        phoneNumberTextField.setBounds(126, 193, 155, 19);
        contentPane.add(phoneNumberTextField);
        phoneNumberTextField.setColumns(10);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        emailTextField.setColumns(10);
        emailTextField.setBounds(126, 158, 155, 19);
        contentPane.add(emailTextField);

        passwordTextField = new JTextField();
        passwordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordTextField.setColumns(10);
        passwordTextField.setBounds(126, 123, 155, 19);
        contentPane.add(passwordTextField);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        usernameTextField.setColumns(10);
        usernameTextField.setBounds(126, 88, 155, 19);
        contentPane.add(usernameTextField);

        roleLabel = new JLabel("role:");
        roleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        roleLabel.setBounds(30, 230, 86, 13);
        contentPane.add(roleLabel);

        Role[] roleTypes = new Role[] {Role.CLIENT, Role.ADMINISTRATOR, Role.EMPLOYEE};
        roleComboBox = new JComboBox<Role>(roleTypes);
        roleComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        roleComboBox.setBounds(126, 227, 155, 21);
        contentPane.add(roleComboBox);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        registerButton.setBounds(65, 265, 185, 21);
        contentPane.add(registerButton);

        loginLabel = new JLabel("Log in:");
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        loginLabel.setBounds(450, 37, 120, 30);
        contentPane.add(loginLabel);

        usernameLogInLabel = new JLabel("username:");
        usernameLogInLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        usernameLogInLabel.setBounds(428, 91, 65, 13);
        contentPane.add(usernameLogInLabel);

        passwordLogInLabel = new JLabel("password:");
        passwordLogInLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordLogInLabel.setBounds(428, 126, 65, 13);
        contentPane.add(passwordLogInLabel);

        usernameLogInTextField = new JTextField();
        usernameLogInTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        usernameLogInTextField.setColumns(10);
        usernameLogInTextField.setBounds(536, 88, 155, 19);
        contentPane.add(usernameLogInTextField);

        passwordLogInTextField = new JTextField();
        passwordLogInTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordLogInTextField.setColumns(10);
        passwordLogInTextField.setBounds(536, 123, 155, 19);
        contentPane.add(passwordLogInTextField);

        loginButton = new JButton("Log in");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        loginButton.setBounds(471, 174, 185, 21);
        contentPane.add(loginButton);

        registerTextLabel = new JLabel("If you don't have an account, you can register");
        registerTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        registerTextLabel.setBounds(30, 296, 265, 83);
        contentPane.add(registerTextLabel);
    }

    public void addRegisterListener(ActionListener actionListener) {
        this.registerButton.addActionListener(actionListener);
    }

    public void addLogInListener(ActionListener actionListener) {
        this.loginButton.addActionListener(actionListener);
    }

    public String getUsernameTextField() {
        return this.usernameTextField.getText();
    }

    public String getPasswordTextField() {
        return this.passwordTextField.getText();
    }

    public String getEmailTextField() {
        return this.emailTextField.getText();
    }

    public String getPhoneNumberTextField() {
        return this.phoneNumberTextField.getText();
    }

    public Role getRoleTextField() {
        return (Role) this.roleComboBox.getSelectedItem();
    }

    public String getUsernameLogInTextField() {
        return this.usernameLogInTextField.getText();
    }

    public String getPasswordLogInTextField() {
        return this.passwordLogInTextField.getText();
    }

    public void writeMessage(String msg) {
        showMessageDialog(contentPane, msg);
    }
}

