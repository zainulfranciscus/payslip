package factory.impl;

import domain.Employee;
import domain.EmployeePayslip;
import domain.Payslip;
import domain.Tax;
import factory.EmployeePayslipFactory;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactoryImpl implements EmployeePayslipFactory {
    public EmployeePayslip createWith(Payslip.MONTH month, Employee employee, Tax tax) {
        return new EmployeePayslip(month,employee,tax);
    }
}
