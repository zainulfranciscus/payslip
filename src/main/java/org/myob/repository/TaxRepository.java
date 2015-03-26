package org.myob.repository;

import org.myob.tax.Tax;
import org.myob.reader.Reader;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Criteria criteria) throws IOException;


    void setReader(Reader mockReader);
}
