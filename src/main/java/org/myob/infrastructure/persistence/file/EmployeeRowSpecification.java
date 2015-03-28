package org.myob.infrastructure.persistence.file;

import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCsvRow;
import org.myob.infrastructure.repository.RowSpecification;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeRowSpecification implements RowSpecification<EmployeeCsvRow>{
    @Override
    public boolean isValid(EmployeeCsvRow row) {
        return row.getPaymentEndDate() != null && row.getPaymentStartDate() != null;
    }
}
