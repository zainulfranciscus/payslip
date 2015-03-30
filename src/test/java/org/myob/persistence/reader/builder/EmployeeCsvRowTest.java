package org.myob.persistence.reader.builder;

import org.junit.Before;
import org.junit.Test;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.builder.EmployeeCsvRowBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.myob.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRowTest {

    private EmployeeCsvRowBuilder employeeCsvRowBuilder;
    private Row csvRow;
    private String expectedFirstName;
    private String expectedLastName;
    private String expectedPaymentDate;
    private String expectedSalary;
    private String expectedSuperRate;
    private String startOfPaymentDate;
    private String startOfPaymentMonth;
    private String startOfPaymentYear;
    private String endOfPaymentDate;
    private String endOfPaymentMonth;
    private String endOfPaymentYear;

    @Before
    public void setup(){

        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSalary = "65000";
        expectedSuperRate = "9%";
        expectedPaymentDate = "01 March – 31 March";

        startOfPaymentDate = "1";
        startOfPaymentMonth = "February";
        startOfPaymentYear = "2015";
        endOfPaymentDate = "3";
        endOfPaymentMonth = "October";
        endOfPaymentYear = "2017";


        employeeCsvRowBuilder = new EmployeeCsvRowBuilder();

        csvRow = employeeCsvRowBuilder.withFirstName(expectedFirstName)
                .withLastName(expectedLastName)
                .withSalary(expectedSalary)
                .withSuper(expectedSuperRate)
                .withPaymentDate(expectedPaymentDate)
                .withStartOfPaymentDate(startOfPaymentDate)
                .withStartOfPaymentMonth(startOfPaymentMonth)
                .withStartOfPaymentYear(startOfPaymentYear)
                .withEndOfPaymentDate(endOfPaymentDate)
                .withEndOfPaymentMonth(endOfPaymentMonth)
                .withEndOfPaymentYear(endOfPaymentYear)
                .build();

    }

    @Test
    public void shouldBeExpectedFirstName(){
        assertEquals(expectedFirstName,csvRow.get(FIRST_NAME));
    }

    @Test
    public void shouldBeExpectedLastName(){
        assertEquals(expectedLastName,csvRow.get(LAST_NAME));
    }

    @Test
    public void shouldBeExpectedSalary(){
        assertEquals(expectedSalary,csvRow.get(ANNUAL_SALARY));
    }

    @Test
    public void shouldBeExpectedSuperRate(){
        assertEquals(expectedSuperRate,csvRow.get(SUPER_RATE));
    }

    @Test
    public void shouldBeExpectedPaymentDate(){
        assertEquals(expectedPaymentDate, csvRow.get(PAYMENT_DATE));
    }

    @Test
    public void shouldReturnStartOfPaymentDate(){
        assertEquals(startOfPaymentDate, csvRow.get(START_PAYMENT_DATE));
    }

    @Test
    public void shouldReturnStartOfPaymentMonth(){
        assertEquals(startOfPaymentMonth,csvRow.get(START_PAYMENT_MONTH));
    }

    @Test
    public void shouldReturnStartOfPaymentYear(){
        assertEquals(startOfPaymentYear,csvRow.get(START_PAYMENT_YEAR));
    }

    @Test
    public void shouldReturnEndOfPaymentDate(){
        assertEquals(endOfPaymentDate, csvRow.get(END_PAYMENT_DATE));
    }

    @Test
    public void shouldReturnEndOfPaymentMonth(){
        assertEquals(endOfPaymentMonth, csvRow.get(END_PAYMENT_MONTH));
    }

    @Test
    public void shouldReturnEndOfPaymentYear(){
        assertEquals(endOfPaymentYear, csvRow.get(END_PAYMENT_YEAR));
    }

    @Test
     public void shouldReturnNullPaymentStartDate_BecausePaymentDateIsInvalid(){
        assertNull(employeeCsvRowBuilder.withStartOfPaymentDate("third of october").build().getPaymentStartDate());
    }

    @Test
      public void shouldReturnNullPaymentStartDate_BecausePaymentMonthIsInvalid(){
        assertNull(employeeCsvRowBuilder.withStartOfPaymentMonth("Every Second Month").build().getPaymentStartDate());
    }

    @Test
    public void shouldReturnNullPaymentStartDate_BecausePaymentYearIsInvalid(){
        assertNull(employeeCsvRowBuilder.withStartOfPaymentYear("3000 B.C").build().getPaymentStartDate());
    }

    @Test
    public void shouldBeTrue_BecausePaymentMonthsIsInFebruary(){

        employeeCsvRowBuilder.withStartOfPaymentMonth("February");
        employeeCsvRowBuilder.withEndOfPaymentMonth("Feb");

        assertTrue(employeeCsvRowBuilder.build().isPaymentDatesInFebruary());
    }

    @Test
    public void shouldBeFalse_BecausePaymentEndMonthsIsNotFebruary(){

        employeeCsvRowBuilder.withStartOfPaymentMonth("February");
        employeeCsvRowBuilder.withEndOfPaymentMonth("August");

        assertFalse(employeeCsvRowBuilder.build().isPaymentDatesInFebruary());
    }

    @Test
    public void shouldBeFalse_BecausePaymentStartMonthsIsNotFebruary(){

        employeeCsvRowBuilder.withStartOfPaymentMonth("October");
        employeeCsvRowBuilder.withEndOfPaymentMonth("February");
        assertFalse(employeeCsvRowBuilder.build().isPaymentDatesInFebruary());
    }

    @Test
    public void shouldBeTrue_BecausePaymentDatesAreInTheSameYear(){


        employeeCsvRowBuilder.withStartOfPaymentYear("2016");
        employeeCsvRowBuilder.withEndOfPaymentYear("2016");
        assertTrue(employeeCsvRowBuilder.build().arePaymentDatesInTheSameYear());
    }

    @Test
    public void shouldBeFalse_BecausePaymentDatesAreInDifferentYear(){

        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentYear("2013");
        assertFalse(employeeCsvRowBuilder.build().arePaymentDatesInTheSameYear());
    }

    @Test
    public void shouldBeFalse_Because2015IsNotALeapYear(){

        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentYear("2015");

        assertFalse(employeeCsvRowBuilder.build().paymentDatesInALeapYear());
    }

    @Test
    public void shouldBeTrue_Because2012IsNotALeapYear(){

        employeeCsvRowBuilder.withStartOfPaymentYear("2012");
        employeeCsvRowBuilder.withEndOfPaymentYear("2012");

        assertTrue(employeeCsvRowBuilder.build().paymentDatesInALeapYear());
    }

    @Test
    public void shouldBeFalse_BecausePaymentDatesAreMoreThan1YearApart() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("July");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("01");
        employeeCsvRowBuilder.withEndOfPaymentMonth("July");
        employeeCsvRowBuilder.withEndOfPaymentYear("2016");
        assertFalse(employeeCsvRowBuilder.build().paymentDatesAreWithinTheSameMonth());
    }

    @Test
    public void shouldBeFalse_BecausePaymentDatesAreLessThan1Month() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("January");
        employeeCsvRowBuilder.withStartOfPaymentYear("2015");
        employeeCsvRowBuilder.withEndOfPaymentDate("30");
        employeeCsvRowBuilder.withEndOfPaymentMonth("January");
        employeeCsvRowBuilder.withEndOfPaymentYear("2015");
        assertFalse(employeeCsvRowBuilder.build().paymentDatesAreWithinTheSameMonth());
    }

    @Test
    public void shouldBeTrue_BecausePaymentAreWithin1MonthInALeapYear() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("February");
        employeeCsvRowBuilder.withStartOfPaymentYear("2012");
        employeeCsvRowBuilder.withEndOfPaymentDate("29");
        employeeCsvRowBuilder.withEndOfPaymentMonth("February");
        employeeCsvRowBuilder.withEndOfPaymentYear("2012");
        assertTrue(employeeCsvRowBuilder.build().paymentDatesAreWithinTheSameMonth());
    }

    @Test
    public void shouldBeTrue_BecausePaymentDates1MonthApart() {

        employeeCsvRowBuilder.withStartOfPaymentDate("01");
        employeeCsvRowBuilder.withStartOfPaymentMonth("July");
        employeeCsvRowBuilder.withStartOfPaymentYear("2012");
        employeeCsvRowBuilder.withEndOfPaymentDate("31");
        employeeCsvRowBuilder.withEndOfPaymentMonth("July");
        employeeCsvRowBuilder.withEndOfPaymentYear("2012");
        assertTrue(employeeCsvRowBuilder.build().paymentDatesAreWithinTheSameMonth());
    }


}
