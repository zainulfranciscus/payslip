package org.myob.reader.tax;

import org.myob.reader.AbstractTestThatWillReturnNullRow;
import org.myob.reader.Reader;
import org.myob.reader.impl.TaxCSVReaderImpl;

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
