package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * MortgageCalculatorView represents the graphical user interface of the mortgage calculator application.
 * It extends JFrame and contains various input fields and buttons for user interaction.
 */

public class MortgageCalculatorView extends JFrame {

    private JTextField principalTextField;
    private JTextField annualInterestRateTextField;
    private JTextField numberOfPaymentsTextField;
    private JComboBox<String> paymentFrequencyComboBox;
    private JComboBox<Integer> compoundingFrequencyComboBox;
    private JButton calculateButton;
    private JTextArea resultTextArea;
    /**
     * Constructs a new MortgageCalculatorView object and initializes the components, layout, and settings.
     */

    public MortgageCalculatorView() {
        setTitle("Mortgage Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponents();
        addComponentsToLayout();

        pack();
        setLocationRelativeTo(null);

    }
    /**
     * Initializes the components for the calculator.
     */

    private void initComponents() {
        principalTextField = new JTextField(10);
        annualInterestRateTextField = new JTextField(10);
        numberOfPaymentsTextField = new JTextField(10);

        paymentFrequencyComboBox = new JComboBox<>(new String[]{"Monthly", "Bi-Weekly", "Weekly"});
        compoundingFrequencyComboBox = new JComboBox<>(new Integer[]{1, 2, 4, 12, 365});

        calculateButton = new JButton("Calculate");

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);

        // Change font and color
        Font customFont = new Font("Arial", Font.PLAIN, 16);
        Color customColor = new Color(63, 81, 181);

        principalTextField.setFont(customFont);
        principalTextField.setForeground(customColor);
        annualInterestRateTextField.setFont(customFont);
        annualInterestRateTextField.setForeground(customColor);
        numberOfPaymentsTextField.setFont(customFont);
        numberOfPaymentsTextField.setForeground(customColor);

        paymentFrequencyComboBox.setFont(customFont);
        paymentFrequencyComboBox.setForeground(customColor);
        compoundingFrequencyComboBox.setFont(customFont);
        compoundingFrequencyComboBox.setForeground(customColor);

        calculateButton.setFont(customFont);
        calculateButton.setForeground(customColor);

        resultTextArea.setFont(customFont);
        resultTextArea.setForeground(customColor);
    }
    /**
     * Adds the components to the layout.
     */


    private void addComponentsToLayout() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Principal:"), constraints);
        constraints.gridx++;
        add(principalTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Annual Interest Rate (%):"), constraints);
        constraints.gridx++;
        add(annualInterestRateTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Number of Payments:"), constraints);
        constraints.gridx++;
        add(numberOfPaymentsTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Payment Frequency:"), constraints);
        constraints.gridx++;
        add(paymentFrequencyComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Compounding Frequency:"), constraints);
        constraints.gridx++;
        add(compoundingFrequencyComboBox, constraints);

        constraints.gridx = 1;
        constraints.gridy++;
        add(calculateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(new JScrollPane(resultTextArea), constraints);
    }
    /**
     * Retrieves the principal amount from the input field.
     *
     * @return a double representing the principal amount
     */

    public double getPrincipal() {
        return Double.parseDouble(principalTextField.getText());
    }
    /**
     * Retrieves the annual interest rate from the input field.
     *
     * @return a double representing the annual interest rate
     */

    public double getAnnualInterestRate() {
        return Double.parseDouble(annualInterestRateTextField.getText()) / 100;
    }
    /**
     * Retrieves the number of payments from the input field.
     *
     * @return an int representing the number of payments
     */

    public int getNumberOfPayments() {
        return Integer.parseInt(numberOfPaymentsTextField.getText());
    }
    /**
     * Retrieves the payment frequency from the combo box.
     *
     * @return an int representing the payment frequency
     */

    public int getPaymentFrequency() {
        String selectedItem = (String) paymentFrequencyComboBox.getSelectedItem();
        if (selectedItem == null) {
            return 12;
        }


        return switch (selectedItem) {
            case "Bi-Weekly" -> 26;
            case "Weekly" -> 52;
            default -> 12;
        };
    }
    /**
     * Retrieves the compounding frequency from the combo box.
     *
     * @return an int representing the compounding frequency
     */

    public int getCompoundingFrequency() {
        Integer selectedItem = (Integer) compoundingFrequencyComboBox.getSelectedItem();
        if (selectedItem == null) {
            return 2;
        }
        return selectedItem;
    }/**
     * Adds an ActionListener for the Calculate button.
     *
     * @param listener the ActionListener to be added
     */

    public void addCalculateButtonListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    } /**
     * Displays the results in the text area.
     *
     * @param results the string representation of the results to be displayed
     */

    public void displayResults(String results) {
        resultTextArea.setText(results);
    }
}
