package org.myob.persistence.reader;

import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Reader {
    Row read() throws Exception;
    void close() throws Exception;
    void setFileName(String fileName) throws IOException;
    void initializeFileReader() throws IOException;
    void setSpecification(RowSpecification specification);
}
