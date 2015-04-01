package org.accounting.persistence.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.accounting.persistence.mapping.impl.TaxHeader;
import org.accounting.persistence.reader.AbstractCsvReader;
import org.accounting.persistence.row.Row;
import org.accounting.persistence.row.builder.TaxCsvRowBuilder;

import java.io.IOException;

import static org.accounting.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends AbstractCsvReader {

    private static final String DEFAULT_TAX_TABLE = "ATO_tax_table.csv";

    @Override
    protected String csvFileFromClasspath() {
        return DEFAULT_TAX_TABLE;
    }

    @Override
    public Row make(CSVRecord record) throws IOException {

        return new TaxCsvRowBuilder()
                .withBaseTax(record.get(BASE_TAX.toString()))
                .withMaxIncome(record.get(MAX_INCOME.toString()))
                .withMinIncome(record.get(MIN_INCOME.toString()))
                .withTaxPerDollar(record.get(TAX_PER_DOLLAR.toString()))
                .withTaxPerDollarOver(record.get(TAX_PER_DOLLAR_OVER.toString()))
                .withStartingDay(record.get(STARTING_DAY.toString()))
                .withStartingMonth(record.get(STARTING_MONTH.toString()))
                .withStartingYear(record.get(STARTING_YEAR.toString()))
                .build();

    }

    @Override
    protected String[] headers() {
        TaxHeader[] taxHeaders = values();
        String [] headers = new String [taxHeaders.length];

        for(int i= 0; i < taxHeaders.length; i++){
            headers[i] = taxHeaders[i].toString();
        }
        return headers;
    }
}
