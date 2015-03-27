package org.myob.reader.employee;

import org.myob.reader.AbstractTestThatWillReturnNullRow;
import org.myob.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.repository.Reader;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TestForEmptyEmployeeCSVFile extends AbstractTestThatWillReturnNullRow {
    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReaderImpl("emptyFile.csv");
    }
}
