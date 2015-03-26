package org.myob.reader.factory;

import org.myob.builder.Builder;
import org.myob.reader.Row;
import org.myob.reader.impl.TaxCsvRow;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvRowBuilder implements Builder<Row> {
    private String minIncome;
    private String maxIncome;
    private String baseTax;
    private String taxPerDollar;
    private String startingYear;
    private String startingMonth;
    private String startingDay;

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

    public Row build() {
        return new TaxCsvRow(minIncome,maxIncome,baseTax,taxPerDollar,startingYear,startingMonth,startingDay);
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
}
