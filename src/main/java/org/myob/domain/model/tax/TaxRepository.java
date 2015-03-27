package org.myob.domain.model.tax;

import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.Specification;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface TaxRepository {

    Tax find(Specification specification) throws IOException;

    void setReader(Reader reader);
}
