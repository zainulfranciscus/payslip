package domain;

import builder.EmployeeBuilder;
import builder.TaxBuilder;
import org.junit.Before;
import org.junit.Test;

import static domain.Payslip.MONTH.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipTest {

    private EmployeePayslip employeePayslip;
    private Employee expectedEmployee;
    private Tax expectedTax;
    private Payslip.MONTH expectedMonth = AUGUST;

    @Before
    public void setup(){

        expectedEmployee = new EmployeeBuilder().withFirstName("Joe").withLastName("Blogg").withSalary(12000).build();
        expectedTax = new TaxBuilder().withBaseTax(1000).withTaxPerDollar(50).withMaxIncome(3000).withMinIncome(100).build();
        employeePayslip = new EmployeePayslip(expectedMonth,expectedEmployee,expectedTax);

    }

    @Test
    public void shouldReturnAPayslipForJoeBlogg(){
       assertEquals(expectedEmployee.getFullName(), employeePayslip.name());
    }

    @Test
    public void shouldReturnAPayslipFor1000AsGrossIncome(){
        assertEquals(expectedEmployee.getSalary()/12,employeePayslip.getGrossIncome());
    }

    @Test
    public void shouldReturnAPayslipForAugust(){
        assertEquals(expectedMonth, employeePayslip.month());
    }

    @Test
    public void shouldReturn2000AsIncomeTax(){
        assertEquals(expectedEmployee.getSalary()/12, employeePayslip.getIncomeTax());
    }


}
