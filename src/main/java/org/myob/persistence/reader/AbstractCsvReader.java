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
    private RowSpecification specification;

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

    @Override
    public Row read() throws IOException {

        Iterator<CSVRecord> recordIterator = records.iterator();

        while(recordIterator.hasNext()){

            Row aRow = make(recordIterator.next());

            if(this.specification == null || aRow.matchesSpecification(specification)){
                return aRow;
            }
        }

        return null;
    }


    @Override
    public void close() throws IOException {
        if(reader != null) {
            reader.close();
        }
    }

    @Override
    public void setSpecification(RowSpecification specification) {
        this.specification = specification;
    }

    protected abstract InputStreamReader loadCsvFileFromClasspath();

    public abstract Row make(CSVRecord record) throws IOException;

}
