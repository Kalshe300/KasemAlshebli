import org.junit.jupiter.api.Test;
import sppcw.Deductions;
import sppcw.Income;
import sppcw.Rate;
import sppcw.TaxReturn;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTests {

    @Test
    void testIncomeTaxBandBasic() {
        Income income = new Income(40000, 5000);
        assertEquals(Rate.BASIC, income.taxBand());
    }

    @Test
    void testIncomeTaxBandHigher() {
        Income income = new Income(60000, 20000);
        assertEquals(Rate.HIGHER, income.taxBand());
    }

    @Test
    void testIncomeTaxBandAdditional() {
        Income income = new Income(160000, 30000);
        assertEquals(Rate.ADDITIONAL, income.taxBand());
    }

    @Test
    void testIncomeTaxBandBoundary1() {
        Income income = new Income(50000, 0);
        assertEquals(Rate.BASIC, income.taxBand());
    }

    @Test
    void testIncomeTaxBandBoundary2() {
        Income income = new Income(140000, 10000);
        assertEquals(Rate.HIGHER, income.taxBand());
    }

    @Test
    void testDeductionsNoDeductions() {
        Deductions deductions = new Deductions();
        assertEquals(0.0, deductions.totalDeductions());
        assertEquals("no deductions", deductions.listOfDeductions());
    }

    @Test
    void testDeductionsSingleDeduction() {
        Deductions deductions = new Deductions();
        deductions.addDeduction("Pension", 2000);
        assertEquals(2000.0, deductions.totalDeductions());
        assertEquals("deductions:,Pension", deductions.listOfDeductions());
    }

    @Test
    void testDeductionsMultipleDeductions() {
        Deductions deductions = new Deductions();
        deductions.addDeduction("Pension", 2000);
        deductions.addDeduction("Charity", 500);
        deductions.addDeduction("Other", 1000);
        assertEquals(1000, deductions.totalDeductions());
        assertEquals("deductions:,Pension,Charity,Other", deductions.listOfDeductions());
    }

    @Test
    void testTaxReturnCalculateTaxableSavingsIncomeBasic() {
        Income income = new Income(40000, 2000);
        Deductions deductions = new Deductions();
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(-1200, taxReturn.calculateTaxableSavingsIncome());
    }

    @Test
    void testTaxReturnCalculateTaxableSavingsIncomeHigher() {
        Income income = new Income(60000, 5000);
        Deductions deductions = new Deductions();
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(-500, taxReturn.calculateTaxableSavingsIncome());
    }

    @Test
    void testTaxReturnCalculateTaxableSavingsIncomeAdditional() {
        Income income = new Income(160000, 10000);
        Deductions deductions = new Deductions();
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(0, taxReturn.calculateTaxableSavingsIncome());
    }

    @Test
    void testTaxReturnCalculateTaxNoDeductionsBasic() {
        Income income = new Income(40000, 2000);
        Deductions deductions = new Deductions();
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(5360, taxReturn.calculateTax());
    }

    @Test
    void testTaxReturnCalculateTaxWithDeductionsHigher() {
        Income income = new Income(60000, 5000);
        Deductions deductions = new Deductions();
        deductions.addDeduction("Pension", 2000);
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(9100, taxReturn.calculateTax());
    }

    @Test
    void testTaxReturnCalculateTaxAdditional() {
        Income income = new Income(160000, 10000);
        Deductions deductions = new Deductions();
        deductions.addDeduction("Charity", 5000);
        TaxReturn taxReturn = new TaxReturn(income, deductions);
        assertEquals(7200, taxReturn.calculateTax());
    }
}