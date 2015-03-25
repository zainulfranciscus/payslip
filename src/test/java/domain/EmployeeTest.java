package domain;

import builder.EmployeeBuilder;
import org.junit.Before;
import org.junit.Test;

import static domain.Employee.DIVISOR_FOR_SUPER_RATE;
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
        employee = new EmployeeBuilder().withFirstName(expectedFirstName).withLastName(expectedLastName).withSalary(expectedSalary).withSuper(expectedSuper).build();
    }

    @Test
    public void shouldReturnJoeAsFirstName() {
        assertEquals(expectedFirstName, employee.getFirstName());

    }

    @Test
    public void shouldReturnBloggAsLastName() {
        assertEquals(expectedLastName, employee.getLastName());
    }

    @Test
    public void shouldReturnJoeBloggAsEmployeeName() {
        assertEquals(employee.getFirstName() + " " + employee.getLastName(), employee.getFullName());
    }

    @Test
    public void shouldReturn1000AsSalary() {
        assertEquals(expectedSalary, employee.getSalary());
    }

    @Test
    public void shouldReturn25AsSuper() {
        assertEquals(expectedSuper, employee.getSuper());

    }

    @Test
    public void shouldReturnAQuarterAsSuperRate() {
        assertEquals(employee.getSuperAsBigDecimal().divide(DIVISOR_FOR_SUPER_RATE), employee.getSuperRate());
    }


}
