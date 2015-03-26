package org.myob.tax;

import org.joda.time.LocalDate;
import org.myob.builder.Builder;
import org.myob.employee.Employee;
import org.myob.repository.impl.TaxCriteria;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCriteriaBuilder implements Builder<TaxCriteria> {

    private Employee employee;

    @Override
    public TaxCriteria build() {
        LocalDate startDate = new LocalDate(employee.getStartOfPaymentYear(),employee.getStartOfPaymentMonth(), employee.getStartOfPaymentDate());
        LocalDate endDate = new LocalDate(employee.getEndOfPaymentYear(), employee.getEndOfPaymentMonth(), employee.getEndOfPaymentDate());

        return new TaxCriteria(employee.getSalary(),startDate,endDate);
    }

    public TaxCriteriaBuilder withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }
}
