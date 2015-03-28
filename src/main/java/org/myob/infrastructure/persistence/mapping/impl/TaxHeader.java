package org.myob.infrastructure.persistence.mapping.impl;

import org.myob.infrastructure.persistence.mapping.RowHeader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */

public enum TaxHeader implements RowHeader {

    MIN_INCOME("Min Income"),
    MAX_INCOME("Max Income"),
    BASE_TAX("Base Tax"),
    TAX_PER_DOLLAR("Tax per Dollar"),
    TAX_PER_DOLLAR_OVER("Tax per Dollar Over"),
    STARTING_DAY("Start Date"),
    STARTING_MONTH("Start Month"),
    STARTING_YEAR("Start Year");

    private String label;

    private TaxHeader(String label){
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
