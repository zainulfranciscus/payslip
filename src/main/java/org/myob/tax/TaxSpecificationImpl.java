package org.myob.tax;

import org.joda.time.LocalDate;
import org.myob.repository.Specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationImpl implements Specification<Tax> {

    private int salary;
    private LocalDate employeePaymentStartDate;
    private LocalDate employeePaymentEndDate;

    public TaxSpecificationImpl(int salary, LocalDate startDate, LocalDate endDate){
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
