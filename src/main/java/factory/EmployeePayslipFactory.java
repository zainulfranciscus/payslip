package factory;

import domain.Employee;
import domain.EmployeePayslip;
import domain.Payslip;
import domain.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeePayslipFactory {

    EmployeePayslip createWith(Payslip.MONTH month, Employee employee, Tax tax);
}
