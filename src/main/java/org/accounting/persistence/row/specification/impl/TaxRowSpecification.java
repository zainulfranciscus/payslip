package org.accounting.persistence.row.specification.impl;

import org.accounting.persistence.row.TaxCsvRow;
import org.accounting.persistence.row.specification.RowSpecification;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class TaxRowSpecification implements RowSpecification<TaxCsvRow> {
    public boolean isValid(TaxCsvRow row) {
        return row.getDate() != null;
    }

}
