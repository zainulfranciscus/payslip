package builder;

import org.junit.Before;
import org.junit.Test;
import reader.Row;

import static org.junit.Assert.assertEquals;
import static reader.EmployeeHeader.*;

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

    @Before
    public void setup(){

        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSalary = "65000";
        expectedSuperRate = "9%";
        expectedPaymentDate = "01 March – 31 March";

        employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        csvRow = employeeCsvRowBuilder.withFirstName(expectedFirstName)
                .withLastName(expectedLastName)
                .withSalary(expectedSalary)
                .withSuper(expectedSuperRate)
                .withPaymentDate(expectedPaymentDate)
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

}
