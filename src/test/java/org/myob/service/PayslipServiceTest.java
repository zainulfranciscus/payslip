package org.myob.service;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.myob.domain.model.employee.*;
import org.myob.service.impl.PayslipServiceImpl;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class PayslipServiceTest {

    private PayslipService payslipService = new PayslipServiceImpl();
    private EmployeeRepository mockEmployeeRepository;

    @Test
    public void shouldProduce1Payslips_BecauseNumberOfEmployeesIs1() throws IOException {

        int numberOfEmployees = 1;

        mockEmployeeRepository = mock(EmployeeRepository.class);
        when(mockEmployeeRepository.countNumberOfEmployees()).thenReturn(numberOfEmployees);

        final EmployeeSpecification employeeSpecification = new EmployeeSpecificationBuilder().withMaxNumberOfLinesShouldBeRead(1).build();

        when(mockEmployeeRepository.find(employeeSpecification)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                employeeSpecification.incrementNumberOfLineRead();
                return null;
            }
        });

        payslipService.setEmployeeRepository(mockEmployeeRepository);
        payslipService.writePayslips(employeeSpecification);
        assertEquals(1, employeeSpecification.numberOfLineRead());

        verify(mockEmployeeRepository,times(1)).find(employeeSpecification);

    }



}
