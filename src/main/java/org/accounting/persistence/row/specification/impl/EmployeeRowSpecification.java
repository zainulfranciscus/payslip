package org.accounting.persistence.row.specification.impl;

import org.accounting.persistence.row.EmployeeCsvRow;
import org.accounting.persistence.row.specification.RowSpecification;

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
