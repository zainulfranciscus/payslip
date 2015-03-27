package org.myob.domain.model.tax;

import org.joda.time.LocalDate;
import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.persistence.Specification;
import org.myob.infrastructure.persistence.TaxSpecification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationImpl implements TaxSpecification {

    private Employee employee;

    public TaxSpecificationImpl(Employee employee){
        this.employee= employee;
    }

    @Override
    public boolean match(Tax tax) {
        LocalDate taxStartDate = new LocalDate(tax.getStartingYear(),tax.getStartingMonth(),tax.getStartingDay());

        return tax.getMinIncome() <= employee.getSalary()
                && tax.getMaxIncome() >=  employee.getSalary()
                && (employee.getPaymentStartDate().isBefore(taxStartDate) || employee.getPaymentStartDate().isEqual(taxStartDate));
    }

    @Override
    public Employee employee() {
        return employee;
    }
}
