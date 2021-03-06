package org.accounting.model.tax;

import org.joda.time.LocalDate;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilder {

    private double minIncome;
    private double maxIncome;
    private double taxPerDollar;
    private double baseTax;
    private double taxPerDollarOver;
    private LocalDate startPeriod;

    public TaxBuilder withMinIncome(double minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    public Tax build() {
        return new Tax(minIncome,
                taxPerDollarOver,
                maxIncome,
                taxPerDollar,
                baseTax,
                startPeriod);
    }

    public TaxBuilder withMaxIncome(double maxIncome) {
        this.maxIncome = maxIncome;
        return this;
    }

    public TaxBuilder withTaxPerDollar(double taxPerDollar) {
        this.taxPerDollar = taxPerDollar;
        return this;
    }

    public TaxBuilder withBaseTax(double baseTax) {
        this.baseTax = baseTax;
        return this;
    }

    public TaxBuilder withStartPeriod(int year, int month, int date){
        this.startPeriod = new LocalDate(year,month,date);
        return  this;
    }

    public TaxBuilder withTaxPerDollarOver(double taxPerDollarOver) {
        this.taxPerDollarOver = taxPerDollarOver;
        return this;
    }
}
