public class Asset {
    private double initialPrice;
    private double depreciationRate;
    private double appreciationRate;

    public Asset(double initialPrice, double depreciationRate, double appreciationRate) {
        this.initialPrice = initialPrice;
        this.depreciationRate = depreciationRate;
        this.appreciationRate = appreciationRate;
    }

    public double calculateDepreciation(int year) {
        return initialPrice * Math.pow((1 - depreciationRate / 100), year);
    }

    public double calculateAppreciation(int year) {
        return initialPrice * Math.pow((1 + appreciationRate / 100), year);
    }
}