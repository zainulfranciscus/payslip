package org.myob.infrastructure.persistence.file.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.myob.infrastructure.repository.RowSpecification;
import org.myob.infrastructure.repository.Reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class AbstractCsvReader implements Reader {

    private java.io.Reader reader;

    public void setDataSourceReader(java.io.Reader inputStreamReader){
        this.reader = inputStreamReader;
    }

    @Override
    public Row read(RowSpecification specification) throws IOException {

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(reader);
        Iterator<CSVRecord> recordIterator = records.iterator();

        boolean hasRecord = recordIterator.hasNext();

        if(!hasRecord){
            return null;
        }

        CSVRecord record = null;
        Row row = null;

        while((row = make(recordIterator.next())) != null){

            if(specification.isValid(row)){
                break;
            }
        }

        return row;
    }

    @Override
    public void close() throws IOException {
        reader.close();

    }

    public abstract Row make(CSVRecord record);

}
