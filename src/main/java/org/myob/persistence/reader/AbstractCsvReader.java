package org.myob.persistence.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class AbstractCsvReader implements Reader {

    protected String datasource;
    protected java.io.Reader reader;
    private Iterable<CSVRecord> records;

    public void setFileName(String datasource) throws IOException {
        this.datasource = datasource;
        initializeFileReader();

    }

    @Override
    public void initializeFileReader() throws IOException {
        this.reader = new FileReader(datasource);
        records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(reader);
    }

    @Override
    public Row read(RowSpecification specification) throws IOException {

        Iterator<CSVRecord> recordIterator = records.iterator();

        boolean hasRecord = recordIterator.hasNext();

        if(!hasRecord){
            return null;
        }

        Row row;

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

    public abstract Row make(CSVRecord record) throws IOException;

}
