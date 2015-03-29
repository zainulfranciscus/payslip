package org.myob.repository.specification;

import org.myob.model.employee.Employee;
import org.myob.model.tax.Tax;
import org.myob.repository.specification.impl.TaxSpecificationImpl;

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
