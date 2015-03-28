package org.myob.infrastructure.persistence.file.reader;

import org.junit.After;
import org.junit.Test;
import org.myob.domain.service.EmployeeSpecification;
import org.myob.infrastructure.persistence.file.EmployeeRowSpecification;
import org.myob.infrastructure.persistence.file.TaxRowSpecification;
import org.myob.infrastructure.repository.Reader;
import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCSVFileReaderImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.myob.infrastructure.persistence.file.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderTest {

    private Reader reader;
    private Row row;

    @After
    public void after() throws Exception {
        reader.close();
    }

    @Test
    public void shouldHave_JoeAsFirstName_BloggAsLastName_12000AsSalary_10PercentAsSuperRate() throws Exception {
        reader = new EmployeeCSVFileReaderImpl( "employee/employee.csv");
        row = reader.read(new EmployeeRowSpecification());

        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveFirstName("Joe")
                .shouldHaveLastName("Blogg")
                .shouldHavePaymentDate("01 March - 31 March")
                .shouldHaveSalary("12000")
                .shouldHaveSuperRate("10%");

    }

    @Test
    public void rowShouldBeNullBecauseCSVOnlyHasHeader() throws Exception {
        reader = new EmployeeCSVFileReaderImpl("employee/onlyHaveEmployeeHeader.csv");
        assertNull(reader.read(new EmployeeRowSpecification()));
    }

    @Test
    public void rowShouldBeNullBecauseFileIsEmpty() throws Exception {
        reader = new EmployeeCSVFileReaderImpl("emptyFile.csv");
        assertNull(reader.read(new EmployeeRowSpecification()));
    }
    class AssertThat{

        AssertThat shouldHaveFirstName(String expectedValue){
            assertEquals(expectedValue,row.get(FIRST_NAME));
            return this;
        }

        AssertThat shouldHaveLastName(String expectedLastName){
            assertEquals(expectedLastName,row.get(LAST_NAME));
            return this;
        }

        AssertThat shouldHaveSalary(String expectedSalary){
            assertEquals(expectedSalary,row.get(ANNUAL_SALARY));
            return this;
        }

        AssertThat shouldHaveSuperRate(String expectedSuper){
            assertEquals(expectedSuper,row.get(SUPER_RATE));
            return this;
        }

        AssertThat shouldHavePaymentDate(String expectedPaymentDate){
            assertEquals(expectedPaymentDate,row.get(PAYMENT_DATE));
            return this;
        }
    }
}
