package reader;

import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForEmptyCSVFiles extends AbstractCSVReaderTest {

    @Override
    public String csvFileName() {
        return "emptyTax.csv";
    }

    @Test
    public void rowReadFromCSVFileShouldBeNull(){
        assertNull(row);
    }
}
