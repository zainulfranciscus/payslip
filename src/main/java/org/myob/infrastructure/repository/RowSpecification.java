package org.myob.infrastructure.repository;

import org.myob.infrastructure.persistence.file.reader.Row;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public interface RowSpecification {
   boolean isValid(Row row);
}
