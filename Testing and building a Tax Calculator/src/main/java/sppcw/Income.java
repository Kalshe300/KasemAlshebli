package sppcw;

public class Income {

    private double workIncome;
    private double savingsIncome;

    private static final double BASIC_RATE_LIMIT = 50000;

    private static final double HIGHER_RATE_LIMIT = 150000;

    public double getWorkIncome() {
        return workIncome;
    }

    public double getSavingsIncome() {
        return savingsIncome;
    }

    public Income(double workIncome, double savingsIncome){
        this.workIncome = workIncome;
        savingsIncome = savingsIncome;
    }

    public Rate taxBand() {
        double totalIncome = workIncome + savingsIncome;
        if (totalIncome <= 50000) {
            return Rate.BASIC;
        } else if (totalIncome <= 150000) {
            return Rate.HIGHER;
        } else {
            return Rate.ADDITIONAL;
        }
    }


}
