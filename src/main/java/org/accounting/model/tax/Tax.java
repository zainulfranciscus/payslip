package org.accounting.model.tax;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Tax {

    private final double baseTax;
    private final double minTaxableIncome;
    private final double maxTaxableIncome;
    private final double taxPerDollarInCents;
    private double taxPerDollarOver;

    private LocalDate startPeriod;


    public Tax(double minTaxableIncome,
               double taxPerDollarOver,
               double maxTaxableIncome,
               double taxPerDollarInCents,
               double baseTax,
               LocalDate startPeriod) {
        this.minTaxableIncome = minTaxableIncome;
        this.taxPerDollarOver = taxPerDollarOver;
        this.maxTaxableIncome = maxTaxableIncome;
        this.taxPerDollarInCents = taxPerDollarInCents;
        this.baseTax = baseTax;
        this.startPeriod = startPeriod;
    }


    public double getMinIncome() {
        return minTaxableIncome;
    }

    public double getMaxIncome() {
        return maxTaxableIncome;
    }

    public double getTaxPerDollarInCents() {
        return taxPerDollarInCents;
    }

    public double getBaseTax() {
        return baseTax;
    }

    public BigDecimal taxDollarInCentsAsBigDecimal() {
        return new BigDecimal(taxPerDollarInCents);
    }

    public BigDecimal baseTaxAsBigDecimal() {
        return new BigDecimal(baseTax);
    }

    public BigDecimal minTaxableIncomeAsBigDecimal() {
        return new BigDecimal(taxPerDollarOver);
    }

    public double getTaxPerDollarOver() {
        return this.taxPerDollarOver;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }
}
