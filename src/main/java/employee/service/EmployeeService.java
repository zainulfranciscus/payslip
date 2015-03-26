package employee.service;

import payslip.MONTH;
import employee.Employee;
import employee.EmployeePayslip;
import repository.TaxRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeeService {

    EmployeePayslip payslip(MONTH month, Employee employee) throws IOException;
    void setTaxRepository(TaxRepository taxRepository);
}
