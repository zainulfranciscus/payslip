package org.myob.domain.model.employee;

import org.joda.time.LocalDate;
import org.myob.domain.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipFactory {
    Payslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax);
}
