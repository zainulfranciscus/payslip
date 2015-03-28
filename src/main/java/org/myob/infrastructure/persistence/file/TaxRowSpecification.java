package org.myob.infrastructure.persistence.file;

import org.myob.infrastructure.persistence.file.reader.impl.TaxCsvRow;
import org.myob.infrastructure.repository.RowSpecification;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class TaxRowSpecification implements RowSpecification<TaxCsvRow>{
    public boolean isValid(TaxCsvRow row) {
        return row.getDate() != null;
    }

}
