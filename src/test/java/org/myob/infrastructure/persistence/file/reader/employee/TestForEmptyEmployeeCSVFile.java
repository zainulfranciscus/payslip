package org.myob.infrastructure.persistence.file.reader.employee;

import org.myob.infrastructure.persistence.file.reader.AbstractTestThatWillReturnNullRow;
import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.infrastructure.persistence.Reader;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TestForEmptyEmployeeCSVFile extends AbstractTestThatWillReturnNullRow {
    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReaderImpl("emptyFile.csv");
    }
}
