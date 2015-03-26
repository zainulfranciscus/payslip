package reader.employee;

import reader.AbstractTestThatWillReturnNullRow;
import reader.impl.EmployeeCSVFileReaderImpl;
import reader.Reader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TestForEmployeeCSVThatOnlyHasHeader extends AbstractTestThatWillReturnNullRow {
    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReaderImpl("employee/onlyHaveEmployeeHeader.csv");
    }
}