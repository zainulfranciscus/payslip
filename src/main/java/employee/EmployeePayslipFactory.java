package employee;

import payslip.MONTH;
import domain.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeePayslipFactory {
    EmployeePayslip createWith(MONTH month, Employee employee, Tax tax);
}
