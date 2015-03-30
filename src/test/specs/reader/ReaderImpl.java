package reader;

import org.myob.persistence.row.Row;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class ReaderImpl implements Reader {

    private List<Row> rows = new ArrayList<>();
    private int index = 0;
    private RowSpecification specification;

    @Override
    public Row read() throws Exception {

        if (index >= rows.size()) {
            return null;
        }

        Row row = rows.get(index);

        if (!specification.isValid(row)) {
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
    public void setFileName(String datasource) {
        return;
    }

    @Override
    public void initializeFileReader() throws IOException {
        index = 0;
    }

    public void add(Row row) {
        rows.add(row);
    }


    @Override
    public void setSpecification(RowSpecification specification) {
        this.specification = specification;
    }
}
