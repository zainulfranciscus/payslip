package org.myob.persistence.row.specification.impl;

import org.myob.persistence.row.TaxCsvRow;
import org.myob.persistence.row.specification.RowSpecification;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class TaxRowSpecification implements RowSpecification<TaxCsvRow> {
    public boolean isValid(TaxCsvRow row) {
        return row.getDate() != null;
    }

}
