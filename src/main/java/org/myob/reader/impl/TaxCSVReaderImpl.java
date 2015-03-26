package org.myob.reader.impl;

import org.myob.reader.AbstractCsvReader;
import org.myob.reader.Row;
import org.myob.reader.TaxHeader;
import org.myob.reader.factory.TaxCsvRowBuilder;
import org.apache.commons.csv.CSVRecord;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends AbstractCsvReader {

    public TaxCSVReaderImpl(String fileName) {
        super(fileName);
    }

    @Override
    public Row make(CSVRecord record) {
        return new TaxCsvRowBuilder()
                .withBaseTax(record.get(TaxHeader.BASE_TAX.getLabel()))
                .withMaxIncome(record.get(TaxHeader.MAX_INCOME.getLabel()))
                .withMinIncome(record.get(TaxHeader.MIN_INCOME.getLabel()))
                .withTaxPerDollar(record.get(TaxHeader.TAX_PER_DOLLAR.getLabel()))
                .withStartingDay(record.get(TaxHeader.STARTING_DAY.getLabel()))
                .withStartingMonth(record.get(TaxHeader.STARTING_MONTH.getLabel()))
                .withStartingYear(record.get(TaxHeader.STARTING_YEAR.getLabel()))
                .build();
    }
}
