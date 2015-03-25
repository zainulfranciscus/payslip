package reader.impl;

import reader.Row;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CsvRow implements Row {

    private final Map<String,Integer> values = new HashMap<String, Integer>();

    public CsvRow(int minIncome, int maxIncome, int baseTax, int taxPerDollar) {
        values.put(MIN_INCOME,minIncome);
        values.put(MAX_INCOME,maxIncome);
        values.put(BASE_TAX,baseTax);
        values.put(TAX_PER_DOLLAR,taxPerDollar);
    }

    @Override
    public int getInt(String columnName) {
        return values.get(columnName);
    }
}
