package org.myob.persistence.reader;

import org.junit.After;
import org.junit.Test;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.myob.persistence.mapping.impl.EmployeeHeader.*;
import static org.myob.persistence.reader.FileReaderType.*;

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
        reader = new EmployeeCSVFileReaderImpl();
        reader.setDataSourceReader(CLASSLOADER.getReader("employee/employee.csv"));
        row = reader.read(new EmployeeRowSpecification());
        
        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveFirstName("Joe")
                .shouldHaveLastName("Blogg")
                .shouldHaveSalary("12000")
                .shouldHaveSuperRate("10%");

    }

    @Test
    public void rowShouldBeNullBecauseCSVOnlyHasHeader() throws Exception {
        reader = new EmployeeCSVFileReaderImpl();
        reader.setDataSourceReader(CLASSLOADER.getReader("employee/onlyHaveEmployeeHeader.csv"));

        assertNull(reader.read(new EmployeeRowSpecification()));
    }

    @Test
    public void rowShouldBeNullBecauseFileIsEmpty() throws Exception {
        reader = new EmployeeCSVFileReaderImpl();
        reader.setDataSourceReader(CLASSLOADER.getReader("emptyFile.csv"));
        assertNull(reader.read(new EmployeeRowSpecification()));
    }

    class AssertThat {

        AssertThat shouldHaveFirstName(String expectedValue) {
            assertEquals(expectedValue, row.get(FIRST_NAME));
            return this;
        }

        AssertThat shouldHaveLastName(String expectedLastName) {
            assertEquals(expectedLastName, row.get(LAST_NAME));
            return this;
        }

        AssertThat shouldHaveSalary(String expectedSalary) {
            assertEquals(expectedSalary, row.get(ANNUAL_SALARY));
            return this;
        }

        AssertThat shouldHaveSuperRate(String expectedSuper) {
            assertEquals(expectedSuper, row.get(SUPER_RATE));
            return this;
        }

    }
}
