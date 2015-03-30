package org.myob.persistence;

import org.junit.Before;
import org.junit.Test;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;
import org.myob.persistence.row.builder.EmployeeCsvRowBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeRowSpecificationTest {

    private EmployeeRowSpecification employeeCsvRowSpecification;
    private EmployeeCsvRowBuilder employeeCsvRowBuilder;

    @Before
    public void setup() {
        employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        employeeCsvRowSpecification = new EmployeeRowSpecification();


    }

    @Test
    public void shouldBeFalse_BecausePaymentStartPeriodHasInvalidDates() {


        employeeCsvRowBuilder.withStartOfPaymentDate("first Of july");
        employeeCsvRowBuilder.withStartOfPaymentMonth("third month of this year");
        employeeCsvRowBuilder.withStartOfPaymentYear("2000 A.D");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecausePaymentEndPeriodHasInvalidDates() {

        employeeCsvRowBuilder.withEndOfPaymentDate("third August");
        employeeCsvRowBuilder.withEndOfPaymentMonth("last month");
        employeeCsvRowBuilder.withEndOfPaymentYear("2000 B.C");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_WhenMonthsIsInAMixCase(){

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("jUlY");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("31");
        employeeCsvRowBuilder.withEndOfPaymentMonth("JuLy");
        employeeCsvRowBuilder.withEndOfPaymentYear("2015");
        assertTrue(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_WhenMonthsIsInShortName(){

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("Oct");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("31");
        employeeCsvRowBuilder.withEndOfPaymentMonth("Oct");
        employeeCsvRowBuilder.withEndOfPaymentYear("2015");
        assertTrue(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecausePaymentDatesAreMoreThan1YearApart() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("July");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("01");
        employeeCsvRowBuilder.withEndOfPaymentMonth("July");
        employeeCsvRowBuilder.withEndOfPaymentYear("2016");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecausePaymentDatesAreLessThan1Month() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("January");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("30");
        employeeCsvRowBuilder.withEndOfPaymentMonth("January");
        employeeCsvRowBuilder.withEndOfPaymentYear("2015");
        assertFalse(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_BecausePaymentAreWithin1MonthInALeapYear() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("February");
        employeeCsvRowBuilder.withStartOfPaymentYear("2012");
        employeeCsvRowBuilder.withEndOfPaymentDate("29");
        employeeCsvRowBuilder.withEndOfPaymentMonth("February");
        employeeCsvRowBuilder.withEndOfPaymentYear("2012");
        assertTrue(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_BecausePaymentDatesAreNotNull_And1MonthApart() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("July");
        employeeCsvRowBuilder.withStartOfPaymentYear("2012");
        employeeCsvRowBuilder.withEndOfPaymentDate("31");
        employeeCsvRowBuilder.withEndOfPaymentMonth("July");
        employeeCsvRowBuilder.withEndOfPaymentYear("2012");
        assertTrue(employeeCsvRowSpecification.isValid(employeeCsvRowBuilder.build()));
    }



}
