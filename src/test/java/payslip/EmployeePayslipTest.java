package payslip;

import domain.Tax;
import employee.EmployeeBuilder;
import builder.TaxBuilder;
import employee.Employee;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipTest {

    private EmployeePayslipImpl employeePayslip;
    private Employee expectedEmployee;
    private Tax expectedTax;
    private MONTH expectedMonth = MONTH.AUGUST;
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

        employeePayslip = new EmployeePayslipImpl(expectedMonth, expectedEmployee, expectedTax);

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
