package org.myob.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.model.payslip.Payslip;
import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;
import org.myob.repository.specification.SpecificationForReadingEmployeeData;
import org.myob.service.impl.PayslipServiceImpl;

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

        payslipService.setPayslipRepository(mockPayslipRepository);
        payslipService.setEmployeeRepository(mockEmployeeRepository);
    }

    @Test
    public void savePayslip_ShouldBeCalled4times() throws Exception {

        int numberOfEmployeesThatCanBeRetrievedIntoMemory = 5;

        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < numberOfEmployeesThatCanBeRetrievedIntoMemory;i++){
            employees.add(new EmployeeBuilder().build());
        }

        ArrayList<Payslip> payslips = new ArrayList<>();
        when(mockPayslipRepository.createPayslips(Mockito.anyList())).thenReturn(payslips);


        final SpecificationForReadingEmployeeData specificationForReadingEmployeeData = setNumberOfEmployeesThatCanBePutIntoMemory(numberOfEmployeesThatCanBeRetrievedIntoMemory);
        when(mockEmployeeRepository.find(specificationForReadingEmployeeData)).thenReturn(employees,employees,employees,employees, new ArrayList<Employee>());


        payslipService.writePayslips(specificationForReadingEmployeeData);
        assertEquals(0, specificationForReadingEmployeeData.numberOfEmployeesLoadedToMemory());

        verify(mockEmployeeRepository,times(5)).find(specificationForReadingEmployeeData);
        verify(mockPayslipRepository,times(4)).savePayslips(payslips);


    }

    @Test
    public void shouldCall_TaxRepository_EmployeeRepositoryClose_1time() throws Exception {
        payslipService.close();
        verify(mockEmployeeRepository,times(1)).close();
        verify(mockPayslipRepository,times(1)).close();
    }


    private SpecificationForReadingEmployeeData setNumberOfEmployeesThatCanBePutIntoMemory(final int maxNumberOfLines) throws Exception {
        final SpecificationForReadingEmployeeData specificationForReadingEmployeeData = new SpecificationForReadingEmployeeData(maxNumberOfLines);

        when(mockEmployeeRepository.find(specificationForReadingEmployeeData)).thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                for(int i = 0; i < maxNumberOfLines; i++) {
                    specificationForReadingEmployeeData.incrementNumberOfEmployeeLoadedToMemory();
                }
                return null;
            }
        });

        return specificationForReadingEmployeeData;
    }



}
