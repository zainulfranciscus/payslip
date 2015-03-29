package org.myob.persistence.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.myob.persistence.mapping.impl.EmployeeHeader.*;
//import static org.myob.persistence.reader.FileReaderType.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderTest {

    private AbstractCsvReader reader;
    private Row row;

    @Before
    public void setup(){
        reader = new EmployeeCSVFileReaderImpl();
    }
    @After
    public void after() throws Exception {
        reader.close();
    }

    @Test
    public void shouldHave_JoeAsFirstName_BloggAsLastName_12000AsSalary_10PercentAsSuperRate() throws Exception {

        reader.setFileName(loadFromClassPath("employee/employee.csv"));
        reader.initializeFileReader();
        row = reader.read(new EmployeeRowSpecification());
        
        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveFirstName("Joe")
                .shouldHaveLastName("Blogg")
                .shouldHaveSalary("12000")
                .shouldHaveSuperRate("10%");

    }

    @Test
    public void rowShouldBeNullBecauseCSVOnlyHasHeader() throws Exception {

        reader.setFileName(loadFromClassPath("employee/onlyHaveEmployeeHeader.csv"));
        reader.initializeFileReader();
        assertNull(reader.read(new EmployeeRowSpecification()));
    }

    @Test
    public void rowShouldBeNullBecauseFileIsEmpty() throws Exception {

        reader.setFileName(loadFromClassPath("emptyFile.csv"));
        reader.initializeFileReader();
        assertNull(reader.read(new EmployeeRowSpecification()));
    }

    @Test
    public void shouldNotReturnNull_BecauseThisCsvFileExistInResourceFolder() throws IOException {

        reader.initializeFileReader();
        reader.setFileName(loadFromClassPath("emptyFile.csv"));
        assertNotNull(reader.loadCsvFileFromClasspath());

    }

    @Test(expected=FileNotFoundException.class)
    public void shouldThrowFileNotFoundException_BecauseThisCsvFileDoesNotExistInResourceFolder() throws IOException {

        reader.setFileName("non_existing_file");
        reader.initializeFileReader();
        assertNull(reader.loadCsvFileFromClasspath());
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

    private String loadFromClassPath(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
