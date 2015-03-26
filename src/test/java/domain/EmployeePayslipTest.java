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
    private String expectedFirstName;
    private String expectedLastName;
    private int expectedSalary;
    private int expectedBaseTax;
    private int expectedTaxPerDollar;
    private int expectedMaxIncome;
    private int expectedMinIncome;

    @Before
    public void setup() {

        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSalary = 12000;
        expectedEmployee = new EmployeeBuilder().withFirstName(expectedFirstName).withLastName(expectedLastName).withSalary(expectedSalary).build();
        expectedBaseTax = 1000;
        expectedTaxPerDollar = 50;
        expectedMaxIncome = 3000;
        expectedMinIncome = 100;

        expectedTax = new TaxBuilder().withBaseTax(expectedBaseTax)
                .withTaxPerDollar(expectedTaxPerDollar)
                .withMaxIncome(expectedMaxIncome)
                .withMinIncome(expectedMinIncome)
                .build();

        employeePayslip = new EmployeePayslip(expectedMonth, expectedEmployee, expectedTax);

    }

    @Test
    public void shouldReturnTheExpectedFirstNameFollowedByExpectedLastName() {
        assertEquals(expectedFirstName + " " + expectedLastName, employeePayslip.name());
    }

    @Test
    public void shouldReturnTheExpectedMonth() {
        assertEquals(expectedMonth, employeePayslip.month());
    }





}
