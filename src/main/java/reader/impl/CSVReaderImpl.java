package reader.impl;

import builder.TaxCsvRowBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import reader.Row;
import reader.Reader;
import reader.TaxHeader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderImpl implements Reader {

    private String fileName;
    private java.io.Reader reader;


    public CSVReaderImpl(String fileName) {
        this.fileName = fileName;

    }

    @Override
    public Row read() throws IOException {
        reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName));
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(reader);
        Iterator<CSVRecord> recordIterator = records.iterator();

        boolean hasRecord = recordIterator.hasNext();
        if(!hasRecord){
            return null;
        }
        CSVRecord record = recordIterator.next();
        Row row = new TaxCsvRowBuilder()
                .withBaseTax(record.get(BASE_TAX.getLabel()))
                .withMaxIncome(record.get(MAX_INCOME.getLabel()))
                .withMinIncome(record.get(MIN_INCOME.getLabel()))
                .withTaxPerDollar(record.get(TAX_PER_DOLLAR.getLabel())).build();

        return row;
    }

    @Override
    public void close() throws IOException {
        reader.close();

    }

}
