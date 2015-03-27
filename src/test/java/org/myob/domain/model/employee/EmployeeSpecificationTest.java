package org.myob.domain.model.employee;

import org.junit.Before;
import org.junit.Test;
import org.myob.infrastructure.persistence.Specification;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationTest {

    private Specification<Employee> employeeSpecification = new EmployeeSpecificationImpl();
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup() {
        employeeBuilder = new EmployeeBuilder()
                .withFirstName("Joe")
                .withLastName("Blogg")
                .withEndOfPaymentDate(31)
                .withEndOfPaymentMonth(12)
                .withEndOfPaymentYear(2015)
                .withStartOfPaymentDate(1)
                .withStartOfPaymentMonth(1)
                .withStartOfPaymentYear(2015);
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

    @Test
    public void employeeWithInvalidStartPaymentDateShouldBeFalse() {
        assertFalse(employeeSpecification.match(employeeBuilder.withStartOfPaymentDate(0).build()));
    }

    @Test
    public void employeeWithInvalidEndPaymentDateShouldBeFalse() {
        assertFalse(employeeSpecification.match(employeeBuilder.withEndOfPaymentDate(0).build()));
    }
}
