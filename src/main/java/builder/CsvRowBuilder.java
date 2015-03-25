package builder;

import reader.impl.CsvRow;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CsvRowBuilder {
    private int minIncome;
    private int maxIncome;
    private int baseTax;
    private int taxPerDollar;

    public CsvRowBuilder withMinIncome(int minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    public CsvRowBuilder withMaxIncome(int maxIncome) {
        this.maxIncome = maxIncome;
        return this;
    }

    public CsvRowBuilder withBaseTax(int baseTax) {
        this.baseTax = baseTax;
        return this;
    }

    public CsvRowBuilder withTaxPerDollar(int taxPerDollar) {
        this.taxPerDollar = taxPerDollar;
        return this;
    }

    public CsvRow build() {
        return new CsvRow(minIncome,maxIncome,baseTax,taxPerDollar);
    }
}
