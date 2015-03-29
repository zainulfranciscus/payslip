package org.myob.repository.specification;

import org.myob.model.employee.Employee;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationBuilder  {

    private Employee employee;

    public TaxSpecification build() {
        return new TaxSpecification(this.employee);
    }

    public TaxSpecificationBuilder withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }
}
