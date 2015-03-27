package org.myob.reader.builder;

import org.junit.Before;
import org.junit.Test;
import org.myob.reader.Row;

import static org.junit.Assert.assertEquals;
import static org.myob.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRowBuilderTest {

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
        startOfPaymentMonth = "2";
        startOfPaymentYear = "2015";
        endOfPaymentDate = "3";
        endOfPaymentMonth = "10";
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

}
