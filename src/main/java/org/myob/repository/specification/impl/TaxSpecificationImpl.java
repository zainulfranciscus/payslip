package org.myob.repository.specification.impl;

import org.joda.time.LocalDate;
import org.myob.model.employee.Employee;
import org.myob.model.tax.Tax;
import org.myob.repository.specification.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationImpl implements Specification<Tax> {

    private Employee employee;

    public TaxSpecificationImpl(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean match(Tax tax) {
        LocalDate taxStartDate = null;
        boolean isValidDate = true;

        try {
            taxStartDate = tax.getStartPeriod();
        } catch (Exception ex) {
            ex.printStackTrace();
            isValidDate = false;
        }

        return isValidDate
                && tax.getMinIncome() <= employee.getSalary()
                && tax.getMaxIncome() >= employee.getSalary()
                && (employee.getPaymentStartDate().isAfter(taxStartDate) || employee.getPaymentStartDate().isEqual(taxStartDate));
    }

}
