package org.myob.domain.model.tax;

import org.joda.time.LocalDate;
import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.repository.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationImpl implements Specification<Tax> {

    private Employee employee;

    public TaxSpecificationImpl(Employee employee){
        this.employee= employee;
    }

    @Override
    public boolean match(Tax tax) {
        LocalDate taxStartDate = new LocalDate(tax.getStartingYear(),tax.getStartingMonth(),tax.getStartingDay());

        return tax.getMinIncome() <= employee.getSalary()
                && tax.getMaxIncome() >=  employee.getSalary()
                && (employee.getPaymentStartDate().isAfter(taxStartDate) || employee.getPaymentStartDate().isEqual(taxStartDate));
    }

}
