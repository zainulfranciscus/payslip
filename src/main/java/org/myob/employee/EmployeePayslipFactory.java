package org.myob.employee;

import org.joda.time.LocalDate;
import org.myob.tax.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeePayslipFactory {
    EmployeePayslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax);
}
