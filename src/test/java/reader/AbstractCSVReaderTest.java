package reader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import reader.impl.CSVReaderImpl;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class  AbstractCSVReaderTest {

    protected static TaxReader reader;
    protected static Row row;


    @BeforeClass
    public static void setup() throws IOException {
        reader = new CSVReaderImpl("tax.csv");
        row = reader.read();
    }

    @AfterClass
    public static void after() throws IOException {
        reader.close();
    }

    public abstract String csvFileName();
}
