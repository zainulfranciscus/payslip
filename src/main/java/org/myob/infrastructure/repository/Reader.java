package org.myob.infrastructure.repository;

import org.myob.infrastructure.persistence.file.reader.Row;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Reader {

    Row read(RowSpecification specification) throws Exception;
    void close() throws Exception;


}
