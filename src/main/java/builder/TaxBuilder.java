package builder;

import domain.Tax;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilder implements Builder<Tax>{

    private int minIncome;
    private int maxIncome;
    private int taxPerDollar;
    private int baseTax;

    public TaxBuilder withMinIncome(int minIncome) {
        this.minIncome = minIncome;
        return this;
    }

    @Override
    public Tax build() {
        return new Tax(minIncome,maxIncome,taxPerDollar,baseTax);
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
}
