package org.myob.repository.specification;

import org.joda.time.LocalDate;
import org.myob.model.employee.Employee;
import org.myob.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecification {

    public static final int NO_MAXIMUM_INCOME = -1;

    private Employee employee;

    public TaxSpecification(Employee employee) {
        this.employee = employee;
    }


    public boolean match(Tax tax) {
        LocalDate taxStartDate = tax.getStartPeriod();
        return  tax.getMinIncome() <= employee.getSalary()
                && (tax.getMaxIncome() >= employee.getSalary() || tax.getMaxIncome() == NO_MAXIMUM_INCOME)
                && (employee.getPaymentStartDate().isAfter(taxStartDate) || employee.getPaymentStartDate().isEqual(taxStartDate));
    }

}
