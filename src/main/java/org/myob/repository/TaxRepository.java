package org.myob.repository;

import org.myob.model.tax.Tax;
import org.myob.persistence.reader.Reader;
import org.myob.repository.specification.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Specification<Tax> specification) throws Exception;

    void setReader(Reader reader);

    void close() throws Exception;
}
