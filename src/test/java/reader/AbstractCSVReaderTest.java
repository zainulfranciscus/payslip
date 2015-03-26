package reader;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class  AbstractCSVReaderTest {

    protected Reader reader;
    protected Row row;


    @Before
    public void setup() throws IOException {
        reader = readerForCSV();
        row = reader.read();
    }

    @After
    public void after() throws IOException {
        reader.close();
    }

    public abstract Reader readerForCSV();

}
