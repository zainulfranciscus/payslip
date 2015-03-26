package reader.employee;

import reader.AbstractTestThatWillReturnNullRow;
import reader.EmployeeCSVFileReader;
import reader.Reader;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TestForEmptyEmployeeCSVFile extends AbstractTestThatWillReturnNullRow {
    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReader("emptyFile.csv");
    }
}
