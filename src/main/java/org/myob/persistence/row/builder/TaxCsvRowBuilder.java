package org.myob.persistence.row.builder;

import org.myob.persistence.row.TaxCsvRow;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvRowBuilder  {
    private String minIncome;
    private String maxIncome;
    private String baseTax;
    private String taxPerDollar;
    private String startingYear;
    private String startingMonth;
    private String startingDay;
    private String taxPerDollarOver;

    public TaxCsvRowBuilder withMinIncome(String minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    public TaxCsvRowBuilder withMaxIncome(String maxIncome) {
        this.maxIncome = maxIncome;
        return this;
    }

    public TaxCsvRowBuilder withBaseTax(String baseTax) {
        this.baseTax = baseTax;
        return this;
    }

    public TaxCsvRowBuilder withTaxPerDollar(String taxPerDollar) {
        this.taxPerDollar = taxPerDollar;
        return this;
    }

    public TaxCsvRowBuilder withStartingYear(String startingYear) {
        this.startingYear = startingYear;
        return this;
    }

    public TaxCsvRowBuilder withStartingMonth(String startingMonth) {
        this.startingMonth = startingMonth;
        return this;
    }

    public TaxCsvRowBuilder withStartingDay(String startingDay) {
        this.startingDay = startingDay;
        return this;
    }

    public TaxCsvRowBuilder withTaxPerDollarOver(String taxPerDollarOver) {
        this.taxPerDollarOver = taxPerDollarOver;
        return this;
    }

    public TaxCsvRow build() {
        return new TaxCsvRow(minIncome,
                maxIncome,
                baseTax,
                taxPerDollar,
                taxPerDollarOver,
                startingYear,
                startingMonth,
                startingDay);
    }
}
