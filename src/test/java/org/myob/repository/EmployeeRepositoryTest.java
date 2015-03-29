package org.myob.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.model.employee.Employee;
import org.myob.persistence.mapping.impl.EmployeeHeader;
import org.myob.repository.specification.EmployeeSpecificationBuilder;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.persistence.row.Row;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.row.specification.RowSpecification;
import org.myob.repository.impl.EmployeeRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.myob.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Reader mockReader;
    private Row mockRow;
    private List<Employee> employees;
    private EmployeeSpecification employeeSpecification;

    private int january;
    private int year2015;
    private int year2014;
    private int december;
    private int thirty1st;
    private int first;
    private int expectedSalary;
    private String expectedFirstName;
    private String expectedLastName;
    private int expectedSuper;

    private LocalDate expectedStartDate;
    private LocalDate expectedEndDate;

    @Before
    public void setup() throws IOException {

        december = 12;
        january = 1;
        year2015 = 2015;
        year2014 = 2014;
        thirty1st = 31;
        first = 1;

        expectedStartDate = new LocalDate(year2014,january,first);
        expectedEndDate = new LocalDate(year2015, december,thirty1st);

        expectedSalary = 12000;
        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSuper = 10;

        mockRow = mock(Row.class);

        when(mockRow.getInt(ANNUAL_SALARY)).thenReturn(expectedSalary);
        when(mockRow.getInt(END_PAYMENT_DATE)).thenReturn(expectedEndDate.getDayOfMonth());
        when(mockRow.getMonthAsInt(END_PAYMENT_MONTH)).thenReturn(expectedEndDate.getMonthOfYear());
        when(mockRow.getInt(END_PAYMENT_YEAR)).thenReturn(expectedEndDate.getYear());
        when(mockRow.getInt(START_PAYMENT_DATE)).thenReturn(expectedStartDate.getDayOfMonth());
        when(mockRow.getMonthAsInt(START_PAYMENT_MONTH)).thenReturn(expectedStartDate.getMonthOfYear());
        when(mockRow.getInt(START_PAYMENT_YEAR)).thenReturn(expectedStartDate.getYear());
        when(mockRow.get(FIRST_NAME)).thenReturn(expectedFirstName);
        when(mockRow.get(LAST_NAME)).thenReturn(expectedLastName);
        when(mockRow.get(SUPER_RATE)).thenReturn(String.valueOf(expectedSuper).concat("%"));

        mockReader = mock(Reader.class);


        employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(mockReader);

    }

    @Test
    public void shouldHaveTheExpected_StartDate_EndDate_FirstName_LastName_Super_Salary_ForAll10Employees() throws Exception {

        int numberOfMockRows = 20;
        int maxNumberOfEmployeesThatShouldBeRead = 10;

        when(mockReader.read(Mockito.isA(RowSpecification.class))).thenReturn(mockRow, listOfRowsWithNullObjectAsTheLastRow(numberOfMockRows));
        employeeSpecification = new  EmployeeSpecificationBuilder().withMaxNumberOfEmployeesThatCanBePutIntoMemory(maxNumberOfEmployeesThatShouldBeRead).build();
        employees = employeeRepository.find(employeeSpecification);

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
        assertEquals(maxNumberOfEmployeesThatShouldBeRead,employeeSpecification.numberOfEmployeesLoadedToMemory());
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
            assertEquals(expectedStartDate,employee.getPaymentStartDate());
            return this;
        }

        public AssertThat hasExpectedEndDate(Employee employee){
            assertEquals(expectedEndDate,employee.getPaymentEndDate());
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
