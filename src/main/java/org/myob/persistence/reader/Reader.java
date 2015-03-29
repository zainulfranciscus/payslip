package org.myob.persistence.reader;

import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Reader {
    Row read(RowSpecification specification) throws Exception;
    void close() throws Exception;
    void setDataSourceReader(java.io.Reader reader) throws IOException;
}
