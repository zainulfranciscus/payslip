package reader;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Reader {

    Row read() throws IOException;
    void close() throws IOException;
}
