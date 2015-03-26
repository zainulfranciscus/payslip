package org.myob.service;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.myob.employee.EmployeePayslip;
import org.myob.employee.EmployeePayslipFactory;
import org.myob.payslip.EmployeePayslipFactoryImpl;
import org.myob.payslip.MONTH;
import org.myob.repository.Criteria;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.TaxCriteria;
import org.myob.service.impl.EmployeeServiceImpl;
import org.myob.tax.Tax;
import org.myob.tax.TaxBuilder;
import org.myob.tax.TaxCriteriaBuilder;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private TaxRepository mockTaxRepository;
    private EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;

    @Before
    public void setup(){

        startDate = new LocalDate(2015,01,01);
        endDate = new LocalDate(2015,10,01);

        mockTaxRepository = mock(TaxRepository.class);
        employeeBuilder = new EmployeeBuilder();

        employeeService = new EmployeeServiceImpl();
        employeeService.setTaxRepository(mockTaxRepository);

        employee = employeeBuilder.withFirstName("Joe")
                .withLastName("Blogg")
                .withSalary(12000)
                .withEndOfPaymentDate(31)
                .withEndOfPaymentMonth(12)
                .withEndOfPaymentYear(2015)
                .withStartOfPaymentDate(01)
                .withStartOfPaymentMonth(01)
                .withEndOfPaymentYear(2015)
                .build();

    }

    @Test
    public void shouldReturnAPayslipWithTheExpectedFullName() throws IOException {

        EmployeePayslip payslip = employeeService.payslip(startDate,endDate,employee);
        assertEquals(employeeBuilder.build().getFullName(), payslip.getEmployeeName());

    }

    @Test
    public void payslipShouldHaveGrossIncomeEqualToAnnualSalaryDividedBy12Months() throws IOException {

        Tax expectedTaxForThisEmployee = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();
        TaxCriteria expectedCriteriaUsedByRepository = new TaxCriteriaBuilder().withEmployee(employee).build();

        LocalDate startDate = new LocalDate(2015,01,01);
        LocalDate endDate = new LocalDate(2015,10,01);

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        EmployeePayslip employeePayslip = payslipFactory.createWith(startDate,endDate, employee,expectedTaxForThisEmployee);

        when(mockTaxRepository.find(expectedCriteriaUsedByRepository)).thenReturn(expectedTaxForThisEmployee);

        EmployeePayslip payslip = employeeService.payslip(startDate,endDate,employee);

        assertEquals(employeePayslip.getGrossIncome(), payslip.getGrossIncome());
        verify(mockTaxRepository, times(1)).find(Mockito.any(Criteria.class));

    }



}
