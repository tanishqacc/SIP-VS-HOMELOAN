public class HomeLoan {
    private double principal;
    private double annualInterestRate;
    private int tenureYears;

    public HomeLoan(double principal, double annualInterestRate, int tenureYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.tenureYears = tenureYears;
    }

    public double calculateEMI() {
        double monthlyInterestRate = annualInterestRate / (12 * 100);
        int tenureMonths = tenureYears * 12;
        return (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);
    }

    public void printAmortizationSchedule() {
        double monthlyInterestRate = annualInterestRate / (12 * 100);
        int tenureMonths = tenureYears * 12;
        double emi = calculateEMI();
        double balance = principal;
        double totalInterestPaid = 0;

        System.out.println("Month\tInterest Paid\tPrincipal Paid\tEnding Balance");

        for (int month = 1; month <= tenureMonths; month++) {
            double interestPaid = balance * monthlyInterestRate;
            double principalPaid = emi - interestPaid;
            balance -= principalPaid;
            totalInterestPaid += interestPaid;

            System.out.printf("%d\t\t%.2f\t\t%.2f\t\t%.2f\n", month, interestPaid, principalPaid, balance);
        }

        System.out.printf("\nTotal Interest Paid: %.2f", totalInterestPaid);
    }
}