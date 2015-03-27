package org.myob.infrastructure.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.EmployeeSpecificationBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.service.EmployeeSpecification;
import org.myob.domain.service.PayslipService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class PayslipServiceTest {

    private PayslipService payslipService = new PayslipServiceImpl();
    private EmployeeRepository mockEmployeeRepository;
    private PayslipRepository mockPayslipRepository;

    @Before
    public void setup(){
        mockEmployeeRepository = mock(EmployeeRepository.class);
        mockPayslipRepository = mock(PayslipRepository.class);
    }

    @Test
    public void shouldCallFind4Times_Because() throws IOException {

        int numberOfEmployeesThatCanBeRetrievedIntoMemory = 5;

        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < numberOfEmployeesThatCanBeRetrievedIntoMemory;i++){
            employees.add(new EmployeeBuilder().build());
        }

        ArrayList<Payslip> payslips = new ArrayList<Payslip>();
        when(mockPayslipRepository.createPayslips(Mockito.anyList())).thenReturn(payslips);


        final EmployeeSpecification employeeSpecification = setNumberOfEmployeesThatCanBePutIntoMemory(numberOfEmployeesThatCanBeRetrievedIntoMemory);
        when(mockEmployeeRepository.find(employeeSpecification)).thenReturn(employees,employees,employees,employees, new ArrayList<Employee>());

        payslipService.setPayslipRepository(mockPayslipRepository);
        payslipService.setEmployeeRepository(mockEmployeeRepository);
        payslipService.writePayslips(employeeSpecification);
        assertEquals(5, employeeSpecification.numberOfEmployeesLoadedToMemory());

        verify(mockEmployeeRepository,times(5)).find(employeeSpecification);
        verify(mockPayslipRepository,times(4)).savePayslips(payslips);


    }

    private EmployeeSpecification setNumberOfEmployeesThatCanBePutIntoMemory(final int maxNumberOfLines) throws IOException {
        final EmployeeSpecification employeeSpecification = new EmployeeSpecificationBuilder().withMaxNumberOfEmployeesThatCanBePutIntoMemory(maxNumberOfLines).build();

        when(mockEmployeeRepository.find(employeeSpecification)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                for(int i = 0; i < maxNumberOfLines; i++) {
                    employeeSpecification.incrementNumberOfLineRead();
                }
                return null;
            }
        });

        return employeeSpecification;
    }



}
