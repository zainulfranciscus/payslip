package org.myob.persistence.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.myob.persistence.row.TaxCsvRow;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.persistence.reader.AbstractCsvReader;
import org.myob.persistence.row.Row;
import org.myob.persistence.mapping.impl.TaxHeader;
import org.myob.persistence.row.builder.TaxCsvRowBuilder;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends AbstractCsvReader {

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
