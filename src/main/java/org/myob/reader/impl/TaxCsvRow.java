package org.myob.reader.impl;

import org.myob.reader.Row;
import org.myob.reader.TaxHeader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvRow extends Row {

    public TaxCsvRow(String minIncome, String maxIncome, String baseTax, String taxPerDollar, String startingYear, String startingMonth, String startingDay) {
        put(TaxHeader.MIN_INCOME,minIncome);
        put(TaxHeader.MAX_INCOME,maxIncome);
        put(TaxHeader.BASE_TAX,baseTax);
        put(TaxHeader.TAX_PER_DOLLAR,taxPerDollar);
        put(TaxHeader.STARTING_DAY,startingDay);
        put(TaxHeader.STARTING_MONTH,startingMonth);
        put(TaxHeader.STARTING_YEAR,startingYear);
    }

}