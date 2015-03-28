package org.myob.domain.model.tax;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Tax {

    private final double baseTax;
    private final double minTaxableIncome;
    private final double maxTaxableIncome;
    private final double taxPerDollarInCents;
    private int startingDay;
    private int startingMonth;
    private int startingYear;
    private double taxPerDollarOver;

    public Tax(double minTaxableIncome,
               double taxPerDollarOver,
               double maxTaxableIncome,
               double taxPerDollarInCents,
               double baseTax,
               int startingDay,
               int startingMonth,
               int startingYear) {
        this.minTaxableIncome = minTaxableIncome;
        this.taxPerDollarOver = taxPerDollarOver;
        this.maxTaxableIncome = maxTaxableIncome;
        this.taxPerDollarInCents = taxPerDollarInCents;
        this.baseTax = baseTax;
        this.startingDay = startingDay;
        this.startingMonth = startingMonth;
        this.startingYear = startingYear;
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

    public BigDecimal taxDollarInCentsAsBigDecimal(){
        return new BigDecimal(taxPerDollarInCents);
    }

    public BigDecimal baseTaxAsBigDecimal(){
        return new BigDecimal(baseTax);
    }
    public BigDecimal minTaxableIncomeAsBigDecimal(){
        return new BigDecimal(taxPerDollarOver);
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

    public double getTaxPerDollarOver() {
        return this.taxPerDollarOver;
    }
}
