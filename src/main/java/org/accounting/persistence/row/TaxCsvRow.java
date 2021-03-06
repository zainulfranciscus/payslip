package org.accounting.persistence.row;

import org.joda.time.LocalDate;

import static org.accounting.persistence.mapping.impl.TaxHeader.*;


/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvRow extends Row {

    public TaxCsvRow(String minIncome,
                     String maxIncome,
                     String baseTax,
                     String taxPerDollar,
                     String taxPerDollarOver,
                     String startingYear,
                     String startingMonth,
                     String startingDay) {
        put(MIN_INCOME,minIncome);
        put(MAX_INCOME,maxIncome);
        put(BASE_TAX,baseTax);
        put(TAX_PER_DOLLAR_OVER,taxPerDollarOver);
        put(TAX_PER_DOLLAR,taxPerDollar);
        put(STARTING_DAY,startingDay);
        put(STARTING_MONTH,startingMonth);
        put(STARTING_YEAR,startingYear);
    }

    public LocalDate getDate(){
        return toLocalDate(get(STARTING_DAY),get(STARTING_MONTH),get(STARTING_YEAR));
    }
}
