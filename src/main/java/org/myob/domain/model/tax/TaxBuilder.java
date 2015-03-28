package org.myob.domain.model.tax;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilder {

    private int minIncome;
    private int maxIncome;
    private double taxPerDollar;
    private int baseTax;
    private int startingDay;
    private int startingMonth;
    private int startingYear;
    private int taxPerDollarOver;

    public TaxBuilder withMinIncome(int minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    public Tax build() {
        return new Tax(minIncome,
                taxPerDollarOver,
                maxIncome,
                taxPerDollar,
                baseTax,
                startingDay,
                startingMonth,
                startingYear);
    }

    public TaxBuilder withMaxIncome(int maxIncome) {
        this.maxIncome = maxIncome;
        return this;
    }

    public TaxBuilder withTaxPerDollar(double taxPerDollar) {
        this.taxPerDollar = taxPerDollar;
        return this;
    }

    public TaxBuilder withBaseTax(int baseTax) {
        this.baseTax = baseTax;
        return this;
    }

    public TaxBuilder withStartingDay(int startingDay) {
        this.startingDay = startingDay;
        return this;
    }

    public TaxBuilder withStartingMonth(int startingMonth) {
        this.startingMonth = startingMonth;
        return this;
    }

    public TaxBuilder withStartingYear(int startingYear) {
        this.startingYear = startingYear;
        return this;
    }

    public TaxBuilder withTaxPerDollarOver(int taxPerDollarOver) {
        this.taxPerDollarOver = taxPerDollarOver;
        return this;
    }
}
