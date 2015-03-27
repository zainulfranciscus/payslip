package org.myob.tax;

import org.joda.time.LocalDate;
import org.myob.builder.Builder;
import org.myob.employee.Employee;
import org.myob.repository.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationBuilder implements Builder<Specification<Tax>> {

    private Employee employee;

    @Override
    public TaxSpecificationImpl build() {
        LocalDate startDate = new LocalDate(employee.getStartOfPaymentYear(),employee.getStartOfPaymentMonth(), employee.getStartOfPaymentDate());
        LocalDate endDate = new LocalDate(employee.getEndOfPaymentYear(), employee.getEndOfPaymentMonth(), employee.getEndOfPaymentDate());

        return new TaxSpecificationImpl(employee.getSalary(),startDate,endDate);
    }

    public TaxSpecificationBuilder withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }
}
