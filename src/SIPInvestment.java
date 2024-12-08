public class SIPInvestment {
    private double monthlyInvestment;
    private double annualReturnRate;
    private int tenureYears;

    public SIPInvestment(double monthlyInvestment, double annualReturnRate, int tenureYears) {
        this.monthlyInvestment = monthlyInvestment;
        this.annualReturnRate = annualReturnRate;
        this.tenureYears = tenureYears;
    }

    public double calculateSIPReturns() {
        double monthlyReturnRate = annualReturnRate / (12 * 100);
        int tenureMonths = tenureYears * 12;
        double futureValue = 0;
        for (int i = 0; i < tenureMonths; i++) {
            futureValue = (futureValue + monthlyInvestment) * (1 + monthlyReturnRate);
        }
        return futureValue;
    }
}