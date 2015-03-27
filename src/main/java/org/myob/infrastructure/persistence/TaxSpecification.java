package org.myob.infrastructure.persistence;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface TaxSpecification extends Specification<Tax>{
   Employee employee();
}
