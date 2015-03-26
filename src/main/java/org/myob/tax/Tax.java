package org.myob.tax;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Tax {

    private final int baseTax;
    private final int minTaxableIncome;
    private final int maxTaxableIncome;
    private final int taxPerDollarInCents;
    private int startingDay;
    private int startingMonth;
    private int startingYear;

    public Tax(int minTaxableIncome,
               int maxTaxableIncome,
               int taxPerDollarInCents,
               int baseTax,
               int startingDay,
               int startingMonth,
               int startingYear) {
        this.minTaxableIncome = minTaxableIncome;
        this.maxTaxableIncome = maxTaxableIncome;
        this.taxPerDollarInCents = taxPerDollarInCents;
        this.baseTax = baseTax;
        this.startingDay = startingDay;
        this.startingMonth = startingMonth;
        this.startingYear = startingYear;
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

    public int getStartingDay() {
        return this.startingDay;
    }

    public int getStartingMonth() {
        return this.startingMonth;
    }

    public int getStartingYear() {
        return this.startingYear;
    }
}
