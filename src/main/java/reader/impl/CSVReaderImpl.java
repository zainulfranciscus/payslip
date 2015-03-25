package reader.impl;

import builder.CsvRowBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import reader.Row;
import reader.TaxReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderImpl implements TaxReader{

    private String fileName;
    private Reader reader;


    public CSVReaderImpl(String fileName) {
        this.fileName = fileName;

    }

    @Override
    public Row read() throws IOException {
        reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName));
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(reader);
        CSVRecord record = records.iterator().next();
        Row row = new CsvRowBuilder()
                .withBaseTax(asInt(record.get(Row.BASE_TAX)))
                .withMaxIncome(asInt(record.get(Row.MAX_INCOME)))
                .withMinIncome(asInt(record.get(Row.MIN_INCOME)))
                .withTaxPerDollar(asInt(record.get(Row.TAX_PER_DOLLAR))).build();

        return row;
    }

    @Override
    public void close() throws IOException {
        reader.close();

    }

    private int asInt(String aRow){
        return Integer.parseInt(aRow);
    }
}
