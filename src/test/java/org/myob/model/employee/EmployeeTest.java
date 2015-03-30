package org.myob.model.employee;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.model.employee.EmployeeBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeTest {

    private EmployeeBuilder employeeBuilder;
    private LocalDate jan1st2015;

    @Before
    public void setup(){
        employeeBuilder = new EmployeeBuilder();
        jan1st2015 = new LocalDate(2015,1,1);
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
    public void shouldCreateEmployeeWithTheExpectedJan1st2015AsStartDate(){

        assertEquals(jan1st2015, employeeBuilder
                .withStartPaymentPeriod(jan1st2015.getYear(), jan1st2015.getMonthOfYear(), jan1st2015.getDayOfMonth())
                .build()
                .getPaymentStartDate());
    }

    @Test
    public void shouldCreateEmployeeWithTheExpectedJan1st2015AsEndDate(){
        assertEquals(jan1st2015, employeeBuilder
                .withEndPaymentPeriod(jan1st2015.getYear(), jan1st2015.getMonthOfYear(), jan1st2015.getDayOfMonth())
                .build()
                .getPaymentEndDate());
    }


    @Test
    public void shouldReturnTheNumericalValueOfTheExpectedSuper(){
        String superRate = "10.5%";
        assertEquals(new Double(10.5), new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }

    @Test
    public void shouldReturnZeroWhenSuperIsEmptyString(){
        String superRate = "";
        assertEquals(new Double(0),new Double(employeeBuilder.withSuperRate(superRate).build().getSuper()));
    }

    @Test
    public void shouldReturnZeroWhenSuperIsWhiteSpace(){
        String superRate = " ";
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
