package org.myob.reader.tax;

import org.myob.reader.AbstractTestThatWillReturnNullRow;
import org.myob.repository.Reader;
import org.myob.reader.impl.TaxCSVReaderImpl;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TestForEmptyTaxCSVFile extends AbstractTestThatWillReturnNullRow {

    @Override
    public Reader readerForCSV() {
        return new TaxCSVReaderImpl("emptyFile.csv");
    }

}
