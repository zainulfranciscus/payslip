package org.accounting.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.accounting.model.employee.Employee;
import org.accounting.persistence.reader.Reader;
import org.accounting.persistence.row.Row;
import org.accounting.repository.impl.EmployeeRepositoryImpl;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.accounting.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Reader mockReader;
    private Row mockRow;
    private List<Employee> employees;
    private SpecificationForReadingEmployeeData specificationForReadingEmployeeData;

    private double expectedSalary;
    private String expectedFirstName;
    private String expectedLastName;
    private double expectedSuper;

    private LocalDate firstJanuary2014;
    private LocalDate dec31st2015;

    private  int maxNumberOfEmployeesThatShouldBeRead = 10;

    @Before
    public void setup() throws IOException {

        firstJanuary2014 = new LocalDate(2014,1,1);
        dec31st2015 = new LocalDate(2015, 12,31);

        expectedSalary = 12000.79;
        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSuper = 10.54;

        mockRow = mock(Row.class);

        when(mockRow.getDouble(ANNUAL_SALARY)).thenReturn(expectedSalary);
        when(mockRow.getInt(END_PAYMENT_DATE)).thenReturn(dec31st2015.getDayOfMonth());
        when(mockRow.getMonthAsInt(END_PAYMENT_MONTH)).thenReturn(dec31st2015.getMonthOfYear());
        when(mockRow.getInt(END_PAYMENT_YEAR)).thenReturn(dec31st2015.getYear());
        when(mockRow.getInt(START_PAYMENT_DATE)).thenReturn(firstJanuary2014.getDayOfMonth());
        when(mockRow.getMonthAsInt(START_PAYMENT_MONTH)).thenReturn(firstJanuary2014.getMonthOfYear());
        when(mockRow.getInt(START_PAYMENT_YEAR)).thenReturn(firstJanuary2014.getYear());
        when(mockRow.get(FIRST_NAME)).thenReturn(expectedFirstName);
        when(mockRow.get(LAST_NAME)).thenReturn(expectedLastName);
        when(mockRow.get(SUPER_RATE)).thenReturn(String.valueOf(expectedSuper).concat("%"));

        mockReader = mock(Reader.class);


        employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(mockReader);

        specificationForReadingEmployeeData = new SpecificationForReadingEmployeeData(maxNumberOfEmployeesThatShouldBeRead);
    }

    @Test
    public void shouldHaveTheExpected_StartDate_EndDate_FirstName_LastName_Super_Salary_ForAll10Employees() throws Exception {

        int numberOfMockRows = 20;
        when(mockReader.read()).thenReturn(mockRow, listOfRowsWithNullObjectAsTheLastRow(numberOfMockRows));
        employees = employeeRepository.find(specificationForReadingEmployeeData);

        assertEquals(maxNumberOfEmployeesThatShouldBeRead, employees.size());
        for(Employee employee: employees){

            AssertThat assertThat = new AssertThat();
            assertThat.hasExpectedEndDate(employee)
                    .hasExpectedFirstName(employee)
                    .hasExpectedLastName(employee)
                    .hasExpectedSalary(employee)
                    .hasExpectedStartDate(employee)
                    .hasExpectedSuper(employee);
        }
        assertEquals(maxNumberOfEmployeesThatShouldBeRead, specificationForReadingEmployeeData.numberOfEmployeesLoadedToMemory());
    }

    @Test
    public void shouldHave0Employee_WhenReaderReturnsNull() throws Exception {
        when(mockReader.read()).thenReturn(null);
        employees = employeeRepository.find(specificationForReadingEmployeeData);
        assertEquals(0, employees.size());
    }

    @Test
    public void shouldCallMockReaderClose1Time() throws Exception {
        employeeRepository.close();
        verify(mockReader,times(1)).close();
    }

    private Row[] listOfRowsWithNullObjectAsTheLastRow(int numberOfMockRow){

        List<Row> mockRows = new ArrayList<Row>();
        for(int i = 0; i < numberOfMockRow; i++){
            mockRows.add(mockRow);
        }
        mockRows.add(null);


        return mockRows.toArray(new Row[mockRows.size()]);
    }

    class AssertThat {

        public AssertThat hasExpectedStartDate(Employee employee){
            assertEquals(firstJanuary2014,employee.getPaymentStartDate());
            return this;
        }

        public AssertThat hasExpectedEndDate(Employee employee){
            assertEquals(dec31st2015,employee.getPaymentEndDate());
            return this;
        }

        public AssertThat hasExpectedFirstName(Employee employee){
            assertEquals(expectedFirstName,employee.getFirstName());
            return this;
        }

        public AssertThat hasExpectedLastName(Employee employee){
            assertEquals(expectedLastName, employee.getLastName());
            return this;
        }

        public AssertThat hasExpectedSuper(Employee employee){
            assertEquals(new Double(expectedSuper), new Double(employee.getSuper()));
            return this;
        }

        public AssertThat hasExpectedSalary(Employee employee){
            assertEquals(new Double(expectedSalary),new Double(employee.getSalary()));
            return this;
        }
    }
}
