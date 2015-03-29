package org.myob;

import asg.cliche.CLIException;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Main.class,ShellFactory.class})
public class MainTest {

    private Main main = new Main();
    private String expectedTaxFileName;
    private String expectedEmployeeFileName;
    private String expectedPayslipFileName;

    @Before
    public void setup() throws CLIException {
        expectedTaxFileName = loadFromClassPath("tax/tax.csv");
        expectedEmployeeFileName = loadFromClassPath("employee/employee.csv");
        expectedPayslipFileName = "payslip.csv";

        main = new Main();
        main.taxFileName(expectedTaxFileName);
        main.employeeFileName(expectedEmployeeFileName);
        main.payslipFileName(expectedPayslipFileName);
    }

    @Test
    public void taxFileNameShouldBeTheExpectedTaxFileName() throws CLIException {
        assertEquals(expectedTaxFileName, main.taxFileName);
    }

    @Test
    public void employeeFileNameShouldBeTheExpectedEmployeeFileName() throws CLIException {
        assertEquals(expectedEmployeeFileName, main.employeeFileName);
    }

    @Test
    public void payslipFileNameShouldBeTheExpectedPayslipFileName() throws CLIException {
        assertEquals(expectedPayslipFileName, main.payslipFileName);
    }

    @Test
    public void callingCheckShouldNotModifyTheExpected_Payslip_tax_AndEmployeeFileName() {
        main.check();
        assertEquals(expectedTaxFileName, main.taxFileName);
        assertEquals(expectedEmployeeFileName, main.employeeFileName);
        assertEquals(expectedPayslipFileName, main.payslipFileName);
    }

    @Test
    public void shouldNotThrowException_WhenTax_Employee_AndPayslipFile_IsNull() throws CLIException {
        main.taxFileName(null);
        main.employeeFileName(null);
        main.payslipFileName(null);

        checkThatNoExceptionIsThrownWhenWritePayslipsIsCalled();
    }
    @Test
    public void shouldNotThrowException_WhenWritingPayslips() throws CLIException {
        main.payslipFileName("payslip.csv");
        checkThatNoExceptionIsThrownWhenWritePayslipsIsCalled();
    }


    @Test
    public void shouldNotThrowException_WhenExecutingMain() throws IOException {

        PowerMockito.mockStatic(ShellFactory.class);
        Shell mockShell = mock(Shell.class);
        when(ShellFactory.createConsoleShell(Mockito.anyString(),Mockito.anyString(),Mockito.isA(Main.class))).thenReturn(mockShell);
        doNothing().when(mockShell).commandLoop();

        boolean exceptionThrown = false;
        try {
            Main.main(new String[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown);
    }

    private void checkThatNoExceptionIsThrownWhenWritePayslipsIsCalled(){
        boolean exceptionThrown = false;
        try {
            main.writePayslips();
        } catch (Exception ex) {
            ex.printStackTrace();
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown);
    }

    private String loadFromClassPath(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
