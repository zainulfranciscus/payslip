package org.myob.infrastructure.persistence.file;

import org.myob.infrastructure.repository.RowSpecification;
import org.myob.infrastructure.persistence.file.reader.Row;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeRowSpecification implements RowSpecification{
    @Override
    public boolean isValid(Row row) {
        return true;
    }
}
