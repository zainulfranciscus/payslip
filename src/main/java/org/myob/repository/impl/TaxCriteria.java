package org.myob.repository.impl;

import org.joda.time.LocalDate;
import org.myob.tax.Tax;
import org.myob.repository.Criteria;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TaxCriteria implements Criteria {

    private int salary;
    private LocalDate employeePaymentStartDate;
    private LocalDate employeePaymentEndDate;

    public TaxCriteria(int salary, LocalDate startDate, LocalDate endDate){
        this.salary = salary;
        this.employeePaymentEndDate=endDate;
        this.employeePaymentStartDate=startDate;
    }

    @Override
    public boolean match(Tax tax) {
        LocalDate taxStartDate = new LocalDate(tax.getStartingYear(),tax.getStartingMonth(),tax.getStartingDay());

        return tax.getMinIncome() <= salary
                && tax.getMaxIncome() >= salary
                && (employeePaymentStartDate.isBefore(taxStartDate) || employeePaymentStartDate.isEqual(taxStartDate));
    }
}
