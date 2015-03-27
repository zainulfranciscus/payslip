package org.myob.infrastructure.persistence.file.reader.tax;

import org.myob.infrastructure.persistence.file.reader.AbstractTestThatWillReturnNullRow;
import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TestForEmptyTaxCSVFile extends AbstractTestThatWillReturnNullRow {

    @Override
    public Reader readerForCSV() {
        return new TaxCSVReaderImpl("emptyFile.csv");
    }

}
