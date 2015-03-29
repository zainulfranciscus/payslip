package org.myob.model.employee;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.model.employee.EmployeeBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeBuilderTest {

    private EmployeeBuilder employeeBuilder;
    private LocalDate localDate;

    @Before
    public void setup(){
        employeeBuilder = new EmployeeBuilder();
        localDate = new LocalDate();
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedFirstName(){
        String expectedFirstName = "Joe";
        assertEquals(expectedFirstName, employeeBuilder.withFirstName(expectedFirstName).build().getFirstName());
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedLastName(){
        String expectedLastName = "Blogg";
        assertEquals(expectedLastName, employeeBuilder.withLastName(expectedLastName).build().getLastName());
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedSuper(){
        int expectedSuper = 10;
        assertEquals(new Double(expectedSuper), new Double(employeeBuilder.withSuper(expectedSuper).build().getSuper()));

    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedSalary(){
        int expectedSalary = 1500;
        assertEquals(new Double(expectedSalary), new Double(employeeBuilder.withSalary(expectedSalary).build().getSalary()));
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedStartDate(){
        int date = 1;
        int month = 1;
        int year = 2015;
        assertEquals(new LocalDate(2015,1,1), employeeBuilder.withStartPaymentPeriod(year,month,date).build().getPaymentStartDate());
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedEndDate(){
        int date = 1;
        int month = 1;
        int year = 2014;

        assertEquals(new LocalDate(2014,1,1), employeeBuilder.withEndPaymentPeriod(year,month,date).build().getPaymentEndDate());
    }


    @Test
    public void shouldReturnTheNumericalValueOfTheExpectedSuper(){
        String superRate = "10.5%";
        assertEquals(new Double(10.5), new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }

    @Test
    public void shouldReturnZeroWhenSuperIsBlank(){
        String superRate = "";
        assertEquals(new Double(0),new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }

    @Test
    public void shouldReturnZeroWhenSuperIsNotANumber(){
        String superRate = "abc";
        assertEquals(new Double(0),new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }

    @Test
    public void superRateShouldBe20AndHalfPercent_IgnoringWhiteSpaces(){
        String superRate = "20.5 %";
        assertEquals(new Double(20.5),new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }
}
