package payslip;


import domain.Tax;
import employee.Employee;
import employee.EmployeePayslip;
import employee.EmployeePayslipFactory;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactoryImpl implements EmployeePayslipFactory {

    public EmployeePayslip createWith(MONTH month, Employee employee, Tax tax) {
        return new EmployeePayslipImpl(month,employee,tax);
    }

}
