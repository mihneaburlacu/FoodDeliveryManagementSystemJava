package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AdministratorView<compositeCreatedSecondLabel> extends JFrame {

    private JPanel contentPane;
    private JLabel importLabel;
    private JButton importButton;
    private JLabel generateLabel;
    private JButton generateButton;
    private JLabel manageLabel;
    private JLabel titleLabel;
    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField caloriesTextField;
    private JTextField proteinTextField;
    private JTextField fatTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel fatLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JLabel titleCompositeLabel;
    private JTextField textField;
    private JLabel titlesBaseProductsLabel;
    private JTextField textField_1;
    private JButton createCompositeButton;
    private JLabel textLabel;
    private JLabel titlesTextLabel;
    private JLabel compositeCreatedSecondLabel;
    private JTextArea compositeCreatedTextField;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JTextField orderCantityTextField;
    private JTextField dayIndexTextField;
    private JLabel orderAmountLabel;
    private JLabel orderValueLabel;
    private JTextField orderAmountTextField;
    private JTextField orderValueTextField;

    public AdministratorView() {
        setTitle("Administrator View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        importLabel = new JLabel("Import the initial set of products");
        importLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        importLabel.setBounds(10, 10, 258, 25);
        contentPane.add(importLabel);

        importButton = new JButton("Import");
        importButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        importButton.setBounds(44, 45, 105, 21);
        contentPane.add(importButton);

        generateLabel = new JLabel("Generate reports about the performed orders");
        generateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        generateLabel.setBounds(10, 81, 280, 25);
        contentPane.add(generateLabel);

        generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        generateButton.setBounds(44, 116, 105, 21);
        contentPane.add(generateButton);

        manageLabel = new JLabel("Manage the products");
        manageLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        manageLabel.setBounds(10, 147, 258, 25);
        contentPane.add(manageLabel);

        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        titleLabel.setBounds(10, 182, 65, 13);
        contentPane.add(titleLabel);

        titleTextField = new JTextField();
        titleTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        titleTextField.setBounds(81, 180, 187, 19);
        contentPane.add(titleTextField);
        titleTextField.setColumns(10);

        ratingTextField = new JTextField();
        ratingTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        ratingTextField.setColumns(10);
        ratingTextField.setBounds(81, 205, 187, 19);
        contentPane.add(ratingTextField);

        caloriesTextField = new JTextField();
        caloriesTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        caloriesTextField.setColumns(10);
        caloriesTextField.setBounds(81, 231, 187, 19);
        contentPane.add(caloriesTextField);

        proteinTextField = new JTextField();
        proteinTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        proteinTextField.setColumns(10);
        proteinTextField.setBounds(81, 258, 187, 19);
        contentPane.add(proteinTextField);

        fatTextField = new JTextField();
        fatTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        fatTextField.setColumns(10);
        fatTextField.setBounds(81, 287, 187, 19);
        contentPane.add(fatTextField);

        sodiumTextField = new JTextField();
        sodiumTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        sodiumTextField.setColumns(10);
        sodiumTextField.setBounds(81, 315, 187, 19);
        contentPane.add(sodiumTextField);

        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        priceTextField.setColumns(10);
        priceTextField.setBounds(81, 344, 187, 19);
        contentPane.add(priceTextField);

        ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ratingLabel.setBounds(10, 209, 65, 13);
        contentPane.add(ratingLabel);

        caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        caloriesLabel.setBounds(10, 235, 65, 13);
        contentPane.add(caloriesLabel);

        proteinLabel = new JLabel("Protein:");
        proteinLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        proteinLabel.setBounds(10, 262, 65, 13);
        contentPane.add(proteinLabel);

        fatLabel = new JLabel("Fat:");
        fatLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        fatLabel.setBounds(10, 291, 65, 13);
        contentPane.add(fatLabel);

        sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        sodiumLabel.setBounds(10, 319, 65, 13);
        contentPane.add(sodiumLabel);

        priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        priceLabel.setBounds(10, 344, 65, 13);
        contentPane.add(priceLabel);

        addButton = new JButton("Add");
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        addButton.setBounds(310, 178, 105, 21);
        contentPane.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        deleteButton.setBounds(310, 230, 105, 21);
        contentPane.add(deleteButton);

        modifyButton = new JButton("Modify");
        modifyButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        modifyButton.setBounds(310, 286, 105, 21);
        contentPane.add(modifyButton);

        titleCompositeLabel = new JLabel("Title for the composite product:");
        titleCompositeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        titleCompositeLabel.setBounds(463, 183, 201, 13);
        contentPane.add(titleCompositeLabel);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textField.setColumns(10);
        textField.setBounds(463, 205, 313, 19);
        contentPane.add(textField);

        titlesBaseProductsLabel = new JLabel("Titles for the base products:");
        titlesBaseProductsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        titlesBaseProductsLabel.setBounds(463, 235, 201, 13);
        contentPane.add(titlesBaseProductsLabel);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textField_1.setColumns(10);
        textField_1.setBounds(463, 259, 313, 19);
        contentPane.add(textField_1);

        createCompositeButton = new JButton("Create composite product");
        createCompositeButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        createCompositeButton.setBounds(483, 312, 280, 25);
        contentPane.add(createCompositeButton);

        textLabel = new JLabel("You are logged in as an admninistrator");
        textLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textLabel.setBounds(547, 3, 239, 39);
        contentPane.add(textLabel);

        titlesTextLabel = new JLabel("(Write the titles with one & between them)");
        titlesTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        titlesTextLabel.setBounds(518, 286, 258, 13);
        contentPane.add(titlesTextLabel);

        compositeCreatedSecondLabel = new JLabel("The composite product: ");
        compositeCreatedSecondLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        compositeCreatedSecondLabel.setBounds(10, 392, 141, 13);
        contentPane.add(compositeCreatedSecondLabel);

        compositeCreatedTextField = new JTextArea();
        compositeCreatedTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        compositeCreatedTextField.setBounds(161, 373, 615, 92);
        contentPane.add(compositeCreatedTextField);
        compositeCreatedTextField.setColumns(10);

        JLabel startHourLabel = new JLabel("Start hour:");
        startHourLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        startHourLabel.setBounds(157, 104, 58, 13);
        contentPane.add(startHourLabel);

        JLabel endHourLabel = new JLabel("End hour:");
        endHourLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        endHourLabel.setBounds(159, 127, 58, 13);
        contentPane.add(endHourLabel);

        startHourTextField = new JTextField();
        startHourTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        startHourTextField.setBounds(225, 101, 96, 19);
        contentPane.add(startHourTextField);
        startHourTextField.setColumns(10);

        endHourTextField = new JTextField();
        endHourTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        endHourTextField.setColumns(10);
        endHourTextField.setBounds(225, 124, 96, 19);
        contentPane.add(endHourTextField);

        JLabel orderCantityLabel = new JLabel("Order Cantity:");
        orderCantityLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderCantityLabel.setBounds(331, 104, 76, 13);
        contentPane.add(orderCantityLabel);

        JLabel dayIndexLabel = new JLabel("Day index:");
        dayIndexLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        dayIndexLabel.setBounds(331, 121, 76, 13);
        contentPane.add(dayIndexLabel);

        orderCantityTextField = new JTextField();
        orderCantityTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderCantityTextField.setColumns(10);
        orderCantityTextField.setBounds(407, 101, 96, 19);
        contentPane.add(orderCantityTextField);

        dayIndexTextField = new JTextField();
        dayIndexTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        dayIndexTextField.setColumns(10);
        dayIndexTextField.setBounds(407, 124, 96, 19);
        contentPane.add(dayIndexTextField);

        JLabel dayTextLabel = new JLabel("(1-Sunday ... 7-Saturday)");
        dayTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        dayTextLabel.setBounds(331, 147, 124, 13);
        contentPane.add(dayTextLabel);

        orderAmountLabel = new JLabel("Order amount:");
        orderAmountLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderAmountLabel.setBounds(518, 104, 76, 13);
        contentPane.add(orderAmountLabel);

        orderValueLabel = new JLabel("Order value:");
        orderValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderValueLabel.setBounds(518, 121, 76, 13);
        contentPane.add(orderValueLabel);

        orderAmountTextField = new JTextField();
        orderAmountTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderAmountTextField.setColumns(10);
        orderAmountTextField.setBounds(591, 101, 96, 19);
        contentPane.add(orderAmountTextField);

        orderValueTextField = new JTextField();
        orderValueTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        orderValueTextField.setColumns(10);
        orderValueTextField.setBounds(591, 124, 96, 19);
        contentPane.add(orderValueTextField);
    }

    public void addImportListener(ActionListener actionListener) {
        this.importButton.addActionListener(actionListener);
    }

    public void addGenerateListener(ActionListener actionListener) {
        this.generateButton.addActionListener(actionListener);
    }

    public void addAddListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }

    public void addDeleteListener(ActionListener actionListener) {
        this.deleteButton.addActionListener(actionListener);
    }

    public void addModifyListener(ActionListener actionListener) {
        this.modifyButton.addActionListener(actionListener);
    }

    public void addCreateCompositeListener(ActionListener actionListener) {
        this.createCompositeButton.addActionListener(actionListener);
    }

    public String getTitle() {
        return this.titleTextField.getText();
    }

    public String getRating() {
        return this.ratingTextField.getText();
    }

    public String getCalories() {
        return this.caloriesTextField.getText();
    }

    public String getProtein() {
        return this.proteinTextField.getText();
    }

    public String getFat() {
        return this.fatTextField.getText();
    }

    public String getSodium() {
        return this.sodiumTextField.getText();
    }

    public String getPrice() {
        return this.priceTextField.getText();
    }

    public void writeMessage(String msg) {
        showMessageDialog(contentPane, msg);
    }

    public void setCompositeProductTextArea(String msg) {
        compositeCreatedTextField.setText(msg);
    }

    public String getTitleCompositeProduct() {
        return textField.getText();
    }

    public String getTitlesCompositeProduct() {
        return textField_1.getText();
    }

    public int getStartHour() {
        return Integer.parseInt(startHourTextField.getText());
    }

    public int getEndHour() {
        return Integer.parseInt(endHourTextField.getText());
    }

    public int getOrderCantity() {
        return Integer.parseInt(orderCantityTextField.getText());
    }

    public int getIndexDay() {
        return Integer.parseInt(dayIndexTextField.getText());
    }

    public int getOrderAmount() {
        return Integer.parseInt(orderAmountTextField.getText());
    }

    public int getOrderValue() {
        return Integer.parseInt(orderValueTextField.getText());
    }

}

