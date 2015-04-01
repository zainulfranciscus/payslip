package org.accounting.persistence.reader;

import org.accounting.persistence.row.Row;
import org.accounting.persistence.row.specification.RowSpecification;

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
