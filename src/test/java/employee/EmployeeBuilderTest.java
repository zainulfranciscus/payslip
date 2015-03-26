package employee;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void paymentStartDateShouldBeTodaysDate(){
        int todayDate = localDate.getDayOfMonth();
        assertEquals(todayDate,employeeBuilder.withStartOfPaymentDate(todayDate).build().getStartOfPaymentDate());
    }

    @Test
    public void paymentStartMonthShouldBeThisMonth(){
        int thisMonth = localDate.getMonthOfYear();
        assertEquals(thisMonth,employeeBuilder.withStartOfPaymentMonth(thisMonth).build().getStartOfPaymentMonth());
    }

    @Test
    public void paymentStartingYearShouldBeThisYear(){
        int thisYear = localDate.getYear();
        assertEquals(thisYear, employeeBuilder.withStartOfPaymentYear(thisYear).build().getStartOfPaymentYear());
    }

    @Test
    public void endOfPaymentYearShouldBe2YearFromNow(){
        int twoYearFromNow = localDate.getYear() + 2;
        assertEquals(twoYearFromNow, employeeBuilder.withEndOfPaymentYear(twoYearFromNow).build().getEndOfPaymentYear());
    }

    @Test
    public void endPaymentMonthShouldBe1MonthFromNow(){
        int aMonthFromNow = localDate.getMonthOfYear() + 1;
        assertEquals(aMonthFromNow, employeeBuilder.withEndOfPaymentMonth(aMonthFromNow).build().getEndOfPaymentMonth());
    }


    @Test
    public void endOfPaymentDateShouldBe3DaysFromNow(){
        int threeDaysFromNow = localDate.getDayOfMonth() + 3;
        assertEquals(threeDaysFromNow, employeeBuilder.withEndOfPaymentDate(threeDaysFromNow).build().getEndOfPaymentDate());
    }
}
