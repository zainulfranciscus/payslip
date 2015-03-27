package org.myob.infrastructure.repository;

import org.joda.time.LocalDate;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipFactory {
    Payslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax);
}
