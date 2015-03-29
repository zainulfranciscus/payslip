package reader;

import org.myob.persistence.row.Row;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.row.specification.RowSpecification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class ReaderImpl implements Reader {

    private List<Row> rows = new ArrayList<>();
    private int index = 0;

    @Override
    public Row read(RowSpecification specification) throws Exception {

        if (index >= rows.size()) {
            return null;
        }

        Row row = rows.get(index);

        if(!specification.isValid(row)){
            return null;
        }
        index += 1;

        return row;

    }

    @Override
    public void close() throws Exception {
        index = 0;
    }

    @Override
    public void setDataSourceReader(java.io.Reader reader) {
        return;
    }

    public void add(Row row) {
        rows.add(row);
    }
}
