package reader.employee;

import reader.AbstractTestThatWillReturnNullRow;
import reader.impl.EmployeeCSVFileReaderImpl;
import reader.Reader;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TestForEmptyEmployeeCSVFile extends AbstractTestThatWillReturnNullRow {
    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReaderImpl("emptyFile.csv");
    }
}
