package org.myob.persistence.row.specification.impl;

import org.joda.time.Months;
import org.myob.persistence.row.EmployeeCsvRow;
import org.myob.persistence.row.specification.RowSpecification;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeRowSpecification implements RowSpecification<EmployeeCsvRow> {
    @Override
    public boolean isValid(EmployeeCsvRow row) {
        return row.getPaymentEndDate() != null
                && row.getPaymentStartDate() != null
                && row.paymentDatesAreWithinTheSameMonth();
    }
}
