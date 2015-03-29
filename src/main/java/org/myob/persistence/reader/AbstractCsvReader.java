package org.myob.persistence.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class AbstractCsvReader implements Reader {

    protected String csvFileName;
    protected java.io.Reader reader;
    protected Iterable<CSVRecord> records;

    @Override
    public void setFileName(String csvFileName) throws IOException {
        this.csvFileName = csvFileName;
    }

    @Override
    public void initializeFileReader() throws IOException {

        if(StringUtils.isNotBlank(csvFileName)) {
            this.reader = new FileReader(csvFileName);
        }else{
            this.reader =  loadCsvFileFromClasspath();
        }
        records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(reader);

    }

    protected abstract InputStreamReader loadCsvFileFromClasspath();


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
