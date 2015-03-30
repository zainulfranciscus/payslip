package org.myob.persistence.mapping.impl;

import org.myob.persistence.mapping.RowHeader;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public enum PayslipHeader implements RowHeader {

    NAME("name"),
    PAY_PERIOD("pay period"),
    GROSS_INCOME("gross income"),
    INCOME_TAX("income tax"),
    NET_INCOME("net income"),
    SUPER("super");

    private String label;

    private PayslipHeader(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }


}
