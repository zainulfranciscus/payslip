package reader.impl;

import builder.TaxCsvRowBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import reader.Reader;
import reader.Row;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class CSVReaderImpl implements Reader {

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

        return make(record);

    }

    @Override
    public void close() throws IOException {
        reader.close();

    }

    public abstract Row make(CSVRecord record);

}
