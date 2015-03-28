package org.myob.infrastructure.persistence.mapping.impl;

import org.myob.infrastructure.persistence.mapping.RowHeader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public enum EmployeeHeader implements RowHeader {

    FIRST_NAME("first name"),
    LAST_NAME("last name"),
    ANNUAL_SALARY("annual salary"),
    SUPER_RATE("super rate"),
    PAYMENT_DATE("payment start date"),
    START_PAYMENT_DATE("payment start date"),
    START_PAYMENT_MONTH("payment start month"),
    START_PAYMENT_YEAR("payment start year"),
    END_PAYMENT_DATE("payment end date"),
    END_PAYMENT_MONTH("payment end month"),
    END_PAYMENT_YEAR("payment end year");

    private String label;

    private EmployeeHeader(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
