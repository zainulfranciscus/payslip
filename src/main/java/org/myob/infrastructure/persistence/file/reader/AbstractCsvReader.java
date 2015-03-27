package org.myob.infrastructure.persistence.file.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.myob.infrastructure.repository.Reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class AbstractCsvReader implements Reader {

    private String fileName;
    private java.io.Reader reader;

    public AbstractCsvReader(String fileName) {
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
