package org.accounting.repository;

import org.accounting.model.tax.Tax;
import org.accounting.persistence.reader.Reader;
import org.accounting.repository.specification.TaxSpecification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(TaxSpecification specification) throws Exception;

    void setReader(Reader reader);

    void close() throws Exception;
}
