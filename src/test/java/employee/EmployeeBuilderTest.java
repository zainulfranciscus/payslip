package employee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeBuilderTest {

    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup(){
        employeeBuilder = new EmployeeBuilder();
    }

    @Test
    public void shouldCreateEmployeeWithJoeAsFirstName(){
        String expectedFirstName = "Joe";
        assertEquals(expectedFirstName, employeeBuilder.withFirstName(expectedFirstName).build().getFirstName());
    }

    @Test
    public void shouldCreateEmployeeWithBloggAsLastName(){
        String expectedLastName = "Blogg";
        assertEquals(expectedLastName, employeeBuilder.withLastName(expectedLastName).build().getLastName());
    }

    @Test
    public void shouldCreateEmployeeWith10AsSuper(){
        int expectedSuper = 10;
        assertEquals(expectedSuper, employeeBuilder.withSuper(expectedSuper).build().getSuper());

    }

    @Test
    public void shouldCreateEmployeeWith1500AsSalary(){
        int expectedSalary = 1500;
        assertEquals(expectedSalary, employeeBuilder.withSalary(expectedSalary).build().getSalary());
    }
}
