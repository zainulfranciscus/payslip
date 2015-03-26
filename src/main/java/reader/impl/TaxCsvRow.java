package reader.impl;

import reader.Row;

import static reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvRow extends Row {

    public TaxCsvRow(String minIncome, String maxIncome, String baseTax, String taxPerDollar) {
        put(MIN_INCOME,minIncome);
        put(MAX_INCOME,maxIncome);
        put(BASE_TAX,baseTax);
        put(TAX_PER_DOLLAR,taxPerDollar);
    }

}
