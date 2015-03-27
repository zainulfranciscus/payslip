package org.myob.domain.model.tax;

import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.repository.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationBuilder  {

    private Employee employee;

    public Specification<Tax> build() {
        return new TaxSpecificationImpl(this.employee);
    }

    public TaxSpecificationBuilder withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }
}
