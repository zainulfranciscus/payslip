package org.myob.infrastructure.repository;

import org.myob.domain.model.tax.Tax;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Specification<Tax> specification) throws Exception;

    void setReader(Reader reader);
}
