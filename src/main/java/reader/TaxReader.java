package reader;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxReader {

    Row read() throws IOException;
    void close() throws IOException;
}
