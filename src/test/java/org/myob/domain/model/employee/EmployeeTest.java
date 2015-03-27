package org.myob.domain.model.employee;

import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;

import static org.myob.domain.model.employee.Employee.DIVISOR_FOR_SUPER_RATE;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeTest {

    private Employee employee;
    private String expectedFirstName = "Joe";
    private String expectedLastName = "Blogg";
    private int expectedSalary = 1000;
    private int expectedSuper = 25;

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
        assertEquals(expectedSalary, employee.getSalary());
    }

    @Test
    public void employeeSuperShouldBeTheExpectedSuper() {
        assertEquals(expectedSuper, employee.getSuper());

    }

    @Test
    public void superRateShouldBeTheExpectedSuperDividedBySuperRate() {
        assertEquals(employee.getSuperAsBigDecimal().divide(DIVISOR_FOR_SUPER_RATE), employee.getSuperRate());
    }

}
