package org.myob.model.payslip;


import org.joda.time.LocalDate;
import org.myob.model.employee.Employee;
import org.myob.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipFactory {

    public static Payslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax) {
        return new Payslip(startPeriod,endPeriod,employee,tax);
    }

}
