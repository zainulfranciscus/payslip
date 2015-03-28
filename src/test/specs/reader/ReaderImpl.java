package reader;

import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.repository.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class ReaderImpl implements Reader {

    private List<Row> rows = new ArrayList<>();
    private int index = 0;

    @Override
    public Row read() throws Exception {

        if (index >= rows.size()) {
            return null;
        }

        Row row = rows.get(index);
        index += 1;

        return row;

    }

    @Override
    public void close() throws Exception {
        index = 0;
    }

    public void add(Row row) {
        rows.add(row);
    }
}
