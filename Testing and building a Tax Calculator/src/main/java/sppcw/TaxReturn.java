package sppcw;

public class TaxReturn {
    private Income income;
    private Deductions deductions;

    private static final double PERSONAL_ALLOWANCE = 12000;

    private static final double BASIC_PERSONAL_SAVINGS_ALLOWANCE = 1200;

    private static final double HIGHER_PERSONAL_SAVINGS_ALLOWANCE = 500;
    private static final double BASIC_RATE_LIMIT = 50000;
    private static final double HIGHER_RATE_LIMIT = 150000;
    private static final double BASIC_RATE = 0.20;
    private static final double HIGHER_RATE = 0.40;
    private static final double ADDITIONAL_RATE = 0.45;

    public TaxReturn(Income income, Deductions deductions) {
        this.income = income;
        this.deductions = deductions;
    }

    public double calculateTaxableSavingsIncome(){
        if (income.taxBand() == Rate.BASIC){
            return (income.getSavingsIncome() - BASIC_PERSONAL_SAVINGS_ALLOWANCE);
        } else if ((income.taxBand() == Rate.HIGHER)){
            return (income.getSavingsIncome() - HIGHER_PERSONAL_SAVINGS_ALLOWANCE);
        } else {
            return income.getSavingsIncome();
        }
    }

    public double calculateTax() {
        double taxableIncome = income.getWorkIncome()
                + calculateTaxableSavingsIncome()
                - deductions.totalDeductions()
                - PERSONAL_ALLOWANCE;

        if (taxableIncome <= BASIC_RATE_LIMIT) {
            return taxableIncome * BASIC_RATE;
        } else if (taxableIncome <= HIGHER_RATE_LIMIT) {
            return (BASIC_RATE_LIMIT * BASIC_RATE) + ((taxableIncome - HIGHER_RATE_LIMIT) * HIGHER_RATE);
        } else {
            return (BASIC_RATE_LIMIT * BASIC_RATE) + ((HIGHER_RATE_LIMIT - BASIC_RATE_LIMIT) * HIGHER_RATE) + ((taxableIncome - BASIC_RATE_LIMIT) * ADDITIONAL_RATE);
        }
    }

}

