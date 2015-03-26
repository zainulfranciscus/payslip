package reader.tax;

import reader.AbstractTestThatWillReturnNullRow;
import reader.Reader;
import reader.impl.TaxCSVReaderImpl;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TestForEmptyTaxCSVFile extends AbstractTestThatWillReturnNullRow {

    @Override
    public Reader readerForCSV() {
        return new TaxCSVReaderImpl("tax/emptyTax.csv");
    }

}
