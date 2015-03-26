package reader.impl;

import builder.TaxCsvRowBuilder;
import org.apache.commons.csv.CSVRecord;
import reader.Row;

import static reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCSVReaderImpl extends CSVReaderImpl{

    public TaxCSVReaderImpl(String fileName) {
        super(fileName);
    }

    @Override
    public Row make(CSVRecord record) {
        return new TaxCsvRowBuilder()
                .withBaseTax(record.get(BASE_TAX.getLabel()))
                .withMaxIncome(record.get(MAX_INCOME.getLabel()))
                .withMinIncome(record.get(MIN_INCOME.getLabel()))
                .withTaxPerDollar(record.get(TAX_PER_DOLLAR.getLabel()))
                .withStartingDay(record.get(STARTING_DAY.getLabel()))
                .withStartingMonth(record.get(STARTING_MONTH.getLabel()))
                .withStartingYear(record.get(STARTING_YEAR.getLabel()))
                .build();
    }
}
