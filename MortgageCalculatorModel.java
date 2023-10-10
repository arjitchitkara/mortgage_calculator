package lab4;

import java.util.Collections;

/**
 * A class representing the model in the mortgage calculator application. The model contains methods for calculating
 * various financial metrics based on a loan amount, interest rate, and number of payments.
 */
public class MortgageCalculatorModel {

    private final double loanAmount;
    private final double interestRatePerYear;
    private final int totalNumberOfPayments;
    private final int paymentsPerYear;
    private final int compoundingPeriodsPerYear;

    /**
     * Constructs a new instance of the mortgage calculator model.
     *
     * @param loanAmount               the total amount of the loan
     * @param interestRatePerYear      the annual interest rate as a decimal
     * @param totalNumberOfPayments    the total number of payments over the life of the loan
     * @param paymentsPerYear          the number of payments per year
     * @param compoundingPeriodsPerYear the number of times interest is compounded per year
     */
    public MortgageCalculatorModel(double loanAmount, double interestRatePerYear, int totalNumberOfPayments, int paymentsPerYear, int compoundingPeriodsPerYear) {
        this.loanAmount = loanAmount;
        this.interestRatePerYear = interestRatePerYear;
        this.totalNumberOfPayments = totalNumberOfPayments;
        this.paymentsPerYear = paymentsPerYear;
        this.compoundingPeriodsPerYear = compoundingPeriodsPerYear;
    }

    /**
     * Calculates the periodic interest rate, which is the interest rate per payment period.
     *
     * @return the periodic interest rate as a decimal
     */
    public double periodicInterestRate() {
        return Math.pow((interestRatePerYear / compoundingPeriodsPerYear) + 1, compoundingPeriodsPerYear / (double) paymentsPerYear) - 1;
    }

    /**
     * Calculates the periodic payment, which is the fixed amount that must be paid each period to fully pay off the loan.
     *
     * @return the periodic payment
     */
    public double periodicPayment() {
        double periodicInterestRate = periodicInterestRate();
        return (loanAmount * periodicInterestRate) / (1 - Math.pow(1 + periodicInterestRate, -totalNumberOfPayments));
    }

    /**
     * Calculates the total amount of interest paid over the life of the loan.
     *
     * @return the total amount of interest paid
     */
    public double getTotalPaidInterest() {
        return (periodicPayment() * totalNumberOfPayments) - loanAmount;
    }

    /**
     * Calculates the total amount of interest and principal paid over the life of the loan.
     *
     * @return the total amount of interest and principal paid
     */
    public double getTotalInterestAndPrincipal() {
        return loanAmount + getTotalPaidInterest();
    }

    /**
     * Calculates the ratio of interest paid to principal paid over the life of the loan.
     *
     * @return the interest/principal ratio
     */
    public double getInterestAndPrincipalRatio() {
        return getTotalPaidInterest() / loanAmount;
    }

    /**
     * Calculates the average amount of interest paid per year.
     *
     * @return the average amount of interest paid per year
     */
    public double getAverageInterestPerYear() {
        return getTotalPaidInterest() / (totalNumberOfPayments / (double) paymentsPerYear);
    }

    /**
     * Calculates the average amount of interest paid per month.
     *
     * @return the average amount of interest paid per month
     */
    public double getAverageInterestPerMonth() {
        return getAverageInterestPerYear() / 12;
    }

/**

 * Calculates the length of the loan in years.
 *
 * @return the length of the loan in years
 */
public double getAmortizationInYears() {
    return totalNumberOfPayments / (double) paymentsPerYear;
}

    /**
     * Generates a payment schedule for the loan, including the periodic payment amount, interest paid, principal paid,
     * and outstanding balance for each payment.
     *
     * @return a formatted string representing the payment schedule
     */
    public String generatePaymentSchedule() {
        double periodicInterestRate = periodicInterestRate();
        double periodicPayment = periodicPayment();
        double outstandingBalance = loanAmount;
        StringBuilder formattedPaymentSchedule = new StringBuilder();

        // Format the header row with fixed width columns
        formattedPaymentSchedule.append(String.format("%-12s | %-18s | %-18s | %-18s | %-18s%n",
                "Payment #", "Periodic Payment", "Periodic Interest", "Periodic Principal Payment", "Outstanding Balance"));

        // Add a separator line
        formattedPaymentSchedule.append(String.join("", Collections.nCopies(90, "-"))).append("\n");

        for (int currentPaymentNumber = 1; currentPaymentNumber <= totalNumberOfPayments; currentPaymentNumber++) {
            double periodicInterest = outstandingBalance * periodicInterestRate;
            double periodicPrincipalPayment = periodicPayment - periodicInterest;
            outstandingBalance -= periodicPrincipalPayment;

            // Set outstanding balance to 0 if it's close enough to 0
            if (Math.abs(outstandingBalance) < 1e-2) {
                outstandingBalance = 0.0;
            }

            // Format each row with fixed width columns
            formattedPaymentSchedule.append(String.format("%-16d | %-16f | %-16f | %-16f | %-16f%n",
                    currentPaymentNumber, periodicPayment, periodicInterest, periodicPrincipalPayment, outstandingBalance));
        }

        // Add the new calculated values after the loop
        formattedPaymentSchedule.append("\nAdditional Information:\n");
        formattedPaymentSchedule.append(String.format("Total Interest Paid: %.2f%n", getTotalPaidInterest()));
        formattedPaymentSchedule.append(String.format("Total Interest and Principal: %.2f%n", getTotalInterestAndPrincipal()));
        formattedPaymentSchedule.append(String.format("Interest/Principal Ratio: %.2f%n", getInterestAndPrincipalRatio()));
        formattedPaymentSchedule.append(String.format("Average Interest Paid per Year: %.2f%n", getAverageInterestPerYear()));
        formattedPaymentSchedule.append(String.format("Average Interest Paid per Month: %.2f%n", getAverageInterestPerMonth()));
        formattedPaymentSchedule.append(String.format("Amortization in Years: %.2f%n", getAmortizationInYears()));
        return formattedPaymentSchedule.toString();
    }
}
