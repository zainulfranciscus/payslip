package org.myob.repository;

import org.myob.tax.Tax;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Specification specification) throws IOException;

    void setReader(Reader reader);
}
