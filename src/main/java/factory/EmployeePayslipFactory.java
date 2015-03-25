package factory;

import domain.Employee;
import domain.EmployeePayslip;
import domain.Payslip;
import domain.Tax;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactory {
    public static EmployeePayslip createWith(Payslip.MONTH month, Employee employee, Tax tax) {
        return new EmployeePayslip(month,employee,tax);
    }
}
