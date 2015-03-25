package domain;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Tax {

    private int baseTax;
    private int minTaxableIncome;
    private int maxTaxableIncome;
    private int taxPerDollarInCents;

    public Tax(int minTaxableIncome, int maxTaxableIncome, int taxPerDollarInCents, int baseTax) {
        this.minTaxableIncome = minTaxableIncome;
        this.maxTaxableIncome = maxTaxableIncome;
        this.taxPerDollarInCents = taxPerDollarInCents;
        this.baseTax = baseTax;
    }

    public int getMinIncome() {
        return minTaxableIncome;
    }

    public int getMaxIncome() {
        return maxTaxableIncome;
    }

    public int getTaxPerDollarInCents() {
        return taxPerDollarInCents;
    }

    public int getBaseTax() {
        return baseTax;
    }

    public BigDecimal taxDollarInCentsAsBigDecimal(){
        return new BigDecimal(taxPerDollarInCents);
    }

    public BigDecimal baseTaxAsBigDecimal(){
        return new BigDecimal(baseTax);
    }
    public BigDecimal minTaxableIncomeAsBigDecimal(){
        return new BigDecimal(minTaxableIncome);
    }

}
