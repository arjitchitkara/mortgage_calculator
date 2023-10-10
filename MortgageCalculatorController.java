package lab4;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MortgageCalculatorController represents the controller of the mortgage calculator application.
 * It handles user input and communicates between the view and the model.
 */

public class MortgageCalculatorController {
    private final MortgageCalculatorView visualization;

    /**
     * Constructs a new MortgageCalculatorController object and initializes the view.
     *
     * @param visualization the MortgageCalculatorView object
     */

    public MortgageCalculatorController(MortgageCalculatorView visualization) {
        this.visualization = visualization;
        visualization.addCalculateButtonListener(new CalculateButtonListener());
    }

    /**
     * CalculateButtonListener listens to the Calculate button's click event and
     * performs the necessary calculations and updates the view with the results.
     */

    class CalculateButtonListener implements ActionListener {
        /**
         * Performs the calculations and updates the view when the Calculate button is clicked.
         *
         * @param e the ActionEvent triggered by clicking the Calculate button
         */
        @Override

        public void actionPerformed(ActionEvent e) {
            try {
                double principal = visualization.getPrincipal();
                double annualInterestRate = visualization.getAnnualInterestRate();
                int numberOfPayments = visualization.getNumberOfPayments();
                int paymentFrequency = visualization.getPaymentFrequency();
                int compoundingFrequency = visualization.getCompoundingFrequency();

                MortgageCalculatorModel model = new MortgageCalculatorModel(principal, annualInterestRate, numberOfPayments,
                        paymentFrequency, compoundingFrequency);

                String paymentSchedule = model.generatePaymentSchedule();

                visualization.displayResults(paymentSchedule);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(visualization, "Please enter valid inputs.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
    /**

     The main entry point for the Mortgage Calculator application.
     This method creates a new instance of MortgageCalculatorView, a graphical user interface
     for the application, and passes it to a new instance of MortgageCalculatorController,
     which controls the behavior of the application. The view is then set to be visible.
     This method utilizes SwingUtilities.invokeLater() to ensure that the user interface
     is created and updated on the Event Dispatch Thread, which is responsible for handling
     all user interface events in Java Swing applications.
     @param args The command line arguments passed to the application, which are not used.
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MortgageCalculatorView view = new MortgageCalculatorView();
            new MortgageCalculatorController(view);
            view.setVisible(true);
        });
    }
}