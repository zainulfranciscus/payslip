package org.myob.infrastructure.persistence.file.reader.tax;

import org.myob.infrastructure.persistence.file.reader.AbstractTestThatWillReturnNullRow;
import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;

import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TestForCSVTaxWithOnlyHeader extends AbstractTestThatWillReturnNullRow {

    @Override
    public Reader readerForCSV() {
        return new TaxCSVReaderImpl("tax/onlyHaveTaxHeaders.csv");
    }

}
