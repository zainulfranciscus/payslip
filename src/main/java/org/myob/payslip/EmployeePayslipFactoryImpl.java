package org.myob.payslip;


import org.joda.time.LocalDate;
import org.myob.tax.Tax;
import org.myob.employee.Employee;
import org.myob.employee.EmployeePayslip;
import org.myob.employee.EmployeePayslipFactory;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactoryImpl implements EmployeePayslipFactory {

    @Override
    public EmployeePayslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax) {
        return new EmployeePayslipImpl(startPeriod,endPeriod,employee,tax);
    }

}
