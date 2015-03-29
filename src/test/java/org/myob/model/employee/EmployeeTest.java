package org.myob.model.employee;

import org.junit.Before;
import org.junit.Test;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;

import static org.myob.model.employee.Employee.DIVISOR_FOR_SUPER_RATE;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeTest {

    private Employee employee;
    private String expectedFirstName = "Joe";
    private String expectedLastName = "Blogg";
    private double expectedSalary = 1750.89;
    private double expectedSuper = 25.44;

    @Before
    public void setup() {

        employee = new EmployeeBuilder()
                .withFirstName(expectedFirstName)
                .withLastName(expectedLastName)
                .withSalary(expectedSalary)
                .withSuper(expectedSuper)
                .build();
    }

    @Test
    public void employeeFirstNameShouldBeTheExpectedFirstName() {
        assertEquals(expectedFirstName, employee.getFirstName());

    }

    @Test
    public void employeeLastNameShouldBeTheExpectedLastName() {
        assertEquals(expectedLastName, employee.getLastName());
    }

    @Test
    public void fullNameIsFirstNameFollowedByLastName() {
        assertEquals(employee.getFirstName() + " " + employee.getLastName(), employee.getFullName());
    }

    @Test
    public void employeeSalaryShouldBeTheExpectedSalary() {
        assertEquals(new Double(expectedSalary), new Double(employee.getSalary()));
    }

    @Test
    public void employeeSuperShouldBeTheExpectedSuper() {
        assertEquals(new Double(expectedSuper), new Double(employee.getSuper()));

    }

    @Test
    public void superRateShouldBeTheExpectedSuperDividedBySuperRate() {
        assertEquals(employee.getSuperAsBigDecimal().divide(DIVISOR_FOR_SUPER_RATE), employee.getSuperRate());
    }

}
