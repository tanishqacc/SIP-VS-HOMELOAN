import java.util.Scanner;

public class InvestmentComparison {
    public static void main(String[] args) {
        // Example values
        Scanner scanner = new Scanner(System.in);

        // Taking inputs from the user
        System.out.print("Enter the home price: ");
        double homePrice = scanner.nextDouble();

        System.out.print("Enter the home loan interest rate (annual %): ");
        double homeLoanInterestRate = scanner.nextDouble();

        System.out.print("Enter the home loan tenure (years): ");
        int homeLoanTenure = scanner.nextInt();

        System.out.print("Enter the depreciation rate (annual %): ");
        double depreciationRate = scanner.nextDouble();

        System.out.print("Enter the appreciation rate (annual %): ");
        double appreciationRate = scanner.nextDouble();

        System.out.print("Enter the monthly SIP amount: ");
        double monthlySIP = scanner.nextDouble();

        System.out.print("Enter the SIP return rate (annual %): ");
        double sipReturnRate = scanner.nextDouble();

        Asset asset = new Asset(homePrice, depreciationRate, appreciationRate);
        HomeLoan homeLoan = new HomeLoan(homePrice, homeLoanInterestRate, homeLoanTenure);
        SIPInvestment sipInvestment = new SIPInvestment(monthlySIP, sipReturnRate, homeLoanTenure);

        double emi = homeLoan.calculateEMI();
        double sipReturns = sipInvestment.calculateSIPReturns();

        System.out.println("\nMonthly Amortization Schedule for Home Loan :\n");

        homeLoan.printAmortizationSchedule();

        System.out.println("\n");

        System.out.println("\nReturn on Investments SIP VS HOMEPURCHASE \n");
        System.out.println("Year\tAppreciated Value\tAsset Depreciation(home)\tSIP Return\t\tProfit on Home loan");
        double totalInterestPaid = 0;
        double endingBalance = homePrice;
        double profitOnHomeLoan=0;
        for (int year = 1; year <= homeLoanTenure; year++) {
            double depreciatedValue = asset.calculateDepreciation(year);
            double appreciatedValue = asset.calculateAppreciation(year);
            SIPInvestment yearlysipInvestment = new SIPInvestment(monthlySIP, sipReturnRate, year);
            double yearlysipReturns = yearlysipInvestment.calculateSIPReturns()-(year*12*monthlySIP);

            double interestPaid = endingBalance * (homeLoanInterestRate / 100);
            totalInterestPaid += interestPaid;
            endingBalance -= (emi * 12 - interestPaid);
            profitOnHomeLoan = appreciatedValue -(homePrice- depreciatedValue) - totalInterestPaid - endingBalance;

            System.out.printf("%d\t\t\t%.2f\t\t\t%.2f\t\t\t\t%.2f\t\t\t%.2f\n", year,appreciatedValue,(homePrice- depreciatedValue ),yearlysipReturns,profitOnHomeLoan);
        }

        System.out.printf("\nTotal Profit on Home Loan over %d years: %.2f\n", homeLoanTenure,profitOnHomeLoan);
        System.out.printf("\nTotal SIP returns over %d years: %.2f\n", homeLoanTenure, sipReturns);
    }
}