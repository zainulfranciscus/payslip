package org.myob.infrastructure.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.service.EmployeeRepository;
import org.myob.domain.model.employee.EmployeeSpecificationBuilder;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.repository.impl.EmployeeRepositoryImpl;
import org.myob.domain.service.EmployeeSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.myob.infrastructure.persistence.file.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Reader mockReader;
    private Row mockRow;
    private List<Employee> employees;
    private Employee employee;
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
        when(mockRow.getInt(END_PAYMENT_MONTH)).thenReturn(expectedEndDate.getMonthOfYear());
        when(mockRow.getInt(END_PAYMENT_YEAR)).thenReturn(expectedEndDate.getYear());
        when(mockRow.getInt(START_PAYMENT_DATE)).thenReturn(expectedStartDate.getDayOfMonth());
        when(mockRow.getInt(START_PAYMENT_MONTH)).thenReturn(expectedStartDate.getMonthOfYear());
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

        when(mockReader.read()).thenReturn(mockRow, listOfRowsWithNullObjectAsTheLastRow(numberOfMockRows));
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
            assertEquals(expectedSuper, employee.getSuper());
            return this;
        }

        public AssertThat hasExpectedSalary(Employee employee){
            assertEquals(expectedSalary,employee.getSalary());
            return this;
        }
    }
}
