package org.myob.infrastructure.persistence;

import org.myob.infrastructure.persistence.file.reader.Row;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Reader {

    Row read() throws IOException;
    void close() throws IOException;
}
