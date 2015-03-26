package org.myob.tax;

import org.myob.builder.Builder;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilder implements Builder<Tax> {

    private int minIncome;
    private int maxIncome;
    private int taxPerDollar;
    private int baseTax;
    private int startingDay;
    private int startingMonth;
    private int startingYear;

    public TaxBuilder withMinIncome(int minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    @Override
    public Tax build() {
        return new Tax(minIncome,
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

    public TaxBuilder withTaxPerDollar(int taxPerDollar) {
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
}
