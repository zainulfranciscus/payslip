package org.myob.infrastructure.persistence.file.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.myob.infrastructure.persistence.file.TaxRowSpecification;
import org.myob.infrastructure.persistence.file.reader.AbstractCsvReader;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.persistence.file.reader.TaxHeader;
import org.myob.infrastructure.persistence.file.reader.builder.TaxCsvRowBuilder;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends AbstractCsvReader {

    public TaxCSVReaderImpl(String fileName) {
        super(fileName);
    }

    @Override
    public Row make(CSVRecord record) {

        TaxCsvRow row = new TaxCsvRowBuilder()
                .withBaseTax(record.get(TaxHeader.BASE_TAX.getLabel()))
                .withMaxIncome(record.get(TaxHeader.MAX_INCOME.getLabel()))
                .withMinIncome(record.get(TaxHeader.MIN_INCOME.getLabel()))
                .withTaxPerDollar(record.get(TaxHeader.TAX_PER_DOLLAR.getLabel()))
                .withTaxPerDollarOver(record.get(TaxHeader.TAX_PER_DOLLAR_OVER.getLabel()))
                .withStartingDay(record.get(TaxHeader.STARTING_DAY.getLabel()))
                .withStartingMonth(record.get(TaxHeader.STARTING_MONTH.getLabel()))
                .withStartingYear(record.get(TaxHeader.STARTING_YEAR.getLabel()))
                .build();

        TaxRowSpecification specification = new TaxRowSpecification();
        if (!specification.isValid(row)) {
            return null;
        }

        return row;
    }
}
