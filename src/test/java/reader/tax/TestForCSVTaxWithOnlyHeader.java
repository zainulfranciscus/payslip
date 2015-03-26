package reader.tax;

import reader.AbstractTestThatWillReturnNullRow;
import reader.Reader;
import reader.impl.TaxCSVReaderImpl;

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
