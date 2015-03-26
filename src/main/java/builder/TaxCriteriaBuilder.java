package builder;

import employee.Employee;
import repository.impl.TaxCriteria;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCriteriaBuilder implements Builder<TaxCriteria>{

    private Employee employee;

    @Override
    public TaxCriteria build() {
        return new TaxCriteria(employee.getSalary());
    }

    public TaxCriteriaBuilder withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }
}
