package org.myob.repository.specification;

import org.junit.Before;
import org.junit.Test;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationTest {

    private Specification<Employee> employeeSpecification;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup() {

        employeeSpecification = new EmployeeSpecificationBuilder().build();

        employeeBuilder = new EmployeeBuilder()
                .withFirstName("Joe")
                .withLastName("Blogg")
                .withEndPaymentPeriod(2015, 12, 31)
                .withStartPaymentPeriod(2015, 1, 1);

    }

    @Test
    public void employeeWithNameAndPaymentDatesIsValid(){
        assertTrue(employeeSpecification.match(employeeBuilder.build()));
    }
    @Test
    public void employeeWithNoFirstNameAndLastNameShouldBeInvalid() {
        assertFalse(employeeSpecification.match(employeeBuilder.withFirstName("").withLastName("").build()));
    }

    @Test
    public void employeeWithAFirstNameButNoLastNameIsValid() {
        assertTrue(employeeSpecification.match(employeeBuilder.withFirstName("").withLastName("Blogg").build()));
    }

    @Test
    public void employeeWithNoFirstNameButWithLastNameIsValid() {
        assertTrue(employeeSpecification.match(employeeBuilder.withFirstName("Joe").withLastName("").build()));

    }

}
