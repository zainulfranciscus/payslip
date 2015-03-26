package repository;

import domain.Tax;
import reader.Reader;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Criteria criteria) throws IOException;


    void setReader(Reader mockReader);
}
