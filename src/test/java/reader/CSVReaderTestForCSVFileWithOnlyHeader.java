package reader;

import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForCSVFileWithOnlyHeader extends  AbstractCSVReaderTest {

    @Override
    public String csvFileName() {
        return "onlyHaveTaxHeaders.csv";
    }

    @Test
    public void rowReadFromCSVFileShouldBeNull(){
        assertNull(row);
    }
}
