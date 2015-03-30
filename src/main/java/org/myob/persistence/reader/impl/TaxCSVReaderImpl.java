package org.myob.persistence.reader.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.myob.persistence.row.TaxCsvRow;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.persistence.reader.AbstractCsvReader;
import org.myob.persistence.row.Row;
import org.myob.persistence.mapping.impl.TaxHeader;
import org.myob.persistence.row.builder.TaxCsvRowBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends AbstractCsvReader {

    private static final String DEFAULT_TAX_TABLE = "ATO_tax_table.csv";

    @Override
    protected InputStreamReader loadCsvFileFromClasspath() {
        return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DEFAULT_TAX_TABLE));
    }

    @Override
    public Row make(CSVRecord record) throws IOException {

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

        return row;
    }
}
