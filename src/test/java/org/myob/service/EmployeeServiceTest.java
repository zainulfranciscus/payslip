package org.myob.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.service.impl.EmployeeServiceImpl;
import org.myob.repository.specification.EmployeeSpecification;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeServiceTest {

    private static final String EMPLOYEE_CSV_FILE = "employee/employee.csv";
    private static final String TAX_CSV_FILE = "tax/tax.csv";
    private static final String PAYSLIP_FILE = "payslip.csv";

    private EmployeeService employeeService;
    private PayslipService mockPayslipService;

    @Before
    public void setup(){
        employeeService = new EmployeeServiceImpl();
        mockPayslipService = mock(PayslipService.class);
        employeeService.setPayslipService(mockPayslipService);

    }

    @Test
    public void shouldCreatePayslipsWithoutThrowingException() throws Exception {
        boolean exceptionThrown = false;

        try {
            employeeService.createPayslips(EMPLOYEE_CSV_FILE, TAX_CSV_FILE, PAYSLIP_FILE);

        } catch (Exception ex) {
            ex.printStackTrace();
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown);
        verify(mockPayslipService,times(1)).writePayslips(Mockito.isA(EmployeeSpecification.class));
        verify(mockPayslipService,times(1)).close();


    }
}
