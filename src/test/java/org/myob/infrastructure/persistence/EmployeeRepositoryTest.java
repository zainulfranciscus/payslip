package org.myob.infrastructure.persistence;

import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeRepository;
import org.myob.domain.model.employee.EmployeeSpecificationImpl;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.persistence.impl.EmployeeRepositoryImpl;

import java.io.IOException;
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
    private EmployeeSpecificationImpl employeeSpecification;

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

    @Before
    public void setup() throws IOException {

        december = 12;
        january = 1;
        year2015 = 2015;
        year2014 = 2014;
        thirty1st = 31;
        first = 1;
        expectedSalary = 12000;
        expectedFirstName = "Joe";
        expectedLastName = "Blogg";
        expectedSuper = 10;

        mockRow = mock(Row.class);

        when(mockRow.getInt(ANNUAL_SALARY)).thenReturn(expectedSalary);
        when(mockRow.getInt(END_PAYMENT_DATE)).thenReturn(thirty1st);
        when(mockRow.getInt(END_PAYMENT_MONTH)).thenReturn(december);
        when(mockRow.getInt(END_PAYMENT_YEAR)).thenReturn(year2015);
        when(mockRow.getInt(START_PAYMENT_DATE)).thenReturn(first);
        when(mockRow.getInt(START_PAYMENT_MONTH)).thenReturn(january);
        when(mockRow.getInt(START_PAYMENT_YEAR)).thenReturn(year2014);
        when(mockRow.get(FIRST_NAME)).thenReturn(expectedFirstName);
        when(mockRow.get(LAST_NAME)).thenReturn(expectedLastName);
        when(mockRow.get(SUPER_RATE)).thenReturn(String.valueOf(expectedSuper).concat("%"));


        mockReader = mock(Reader.class);
        when(mockReader.read()).thenReturn(mockRow,null);

        employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(mockReader);

        employeeSpecification = new EmployeeSpecificationImpl();
        employees = employeeRepository.find(employeeSpecification);
        employee = employees.get(0);

    }

    @Test
    public void shouldReturnJanuaryAsStartingMonth(){
        assertEquals(january, employee.getStartOfPaymentMonth());
    }

    @Test
    public void shouldReturnDecemberAsEndMonth(){
        assertEquals(december,employee.getEndOfPaymentMonth());
    }

    @Test
    public void shouldReturnYear2014AsStartingYear(){
        assertEquals(year2014, employee.getStartOfPaymentYear());
    }

    @Test
    public void shouldReturnYear2015AsEndYear(){
        assertEquals(year2015, employee.getEndOfPaymentYear());
    }

    @Test
    public void shouldReturnThirty1stForEndPaymentDate(){
        assertEquals(thirty1st, employee.getEndOfPaymentDate());
    }

    @Test
    public void shouldReturnFirstForStartPaymentDate(){
        assertEquals(first,employee.getStartOfPaymentDate());
    }

    @Test
    public void shouldReturnExpectedSalary(){
        assertEquals(expectedSalary,employee.getSalary());
    }

    @Test
    public void shouldReturnExpectedFirstName(){
        assertEquals(expectedFirstName,employee.getFirstName());
    }

    @Test
    public void shouldReturnExpectedLastName(){
        assertEquals(expectedLastName,employee.getLastName());
    }

    @Test
    public void shouldReturnExpectedSuper(){
        assertEquals(expectedSuper,employee.getSuper());
    }

}
