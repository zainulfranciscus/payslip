package org.myob.application;

import org.junit.Test;
import org.myob.application.service.EmployeeService;
import org.myob.application.service.impl.EmployeeServiceImpl;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeServiceTest {

    private static final String EMPLOYEE_CSV_FILE = "employee/employee.csv";
    private static final String TAX_CSV_FILE = "tax/tax.csv";

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldReturnEmployeeFromEmployeeCSVFile_withoutThrowAnException() {
        boolean exceptionThrown = false;

        try {
            String payslipFile = "payslip.csv";
            employeeService.createPayslips(EMPLOYEE_CSV_FILE, TAX_CSV_FILE, payslipFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            exceptionThrown = true;

        }

        assertFalse(exceptionThrown);


    }
}
