package org.myob.infrastructure.persistence.file;

import org.junit.Test;
import org.myob.infrastructure.persistence.file.reader.builder.EmployeeCsvRowBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeRowSpecificationTest {

    private EmployeeRowSpecification employeeCsvRowSpecification = new EmployeeRowSpecification();

    @Test
    public void shouldBeFalse_BecausePaymentStartPeriodHasInvalidDates(){

        EmployeeCsvRowBuilder employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        employeeCsvRowBuilder.withStartOfPaymentDate("first Of july");
        employeeCsvRowBuilder.withStartOfPaymentMonth("third month of this year");
        employeeCsvRowBuilder.withStartOfPaymentYear("2000 A.D");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecausePaymentEndPeriodHasInvalidDates(){
        EmployeeCsvRowBuilder employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        employeeCsvRowBuilder.withEndOfPaymentDate("third August");
        employeeCsvRowBuilder.withEndOfPaymentMonth("last month");
        employeeCsvRowBuilder.withEndOfPaymentYear("2000 B.C");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_BecausePaymentDatesAreValid(){
        EmployeeCsvRowBuilder employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("July");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("03");
        employeeCsvRowBuilder.withEndOfPaymentMonth("August");
        employeeCsvRowBuilder.withEndOfPaymentYear("2016");
        assertTrue(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }
}
