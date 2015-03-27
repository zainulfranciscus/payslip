package org.myob.service;

import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.myob.employee.EmployeePayslip;
import org.myob.employee.EmployeePayslipFactory;
import org.myob.payslip.EmployeePayslipFactoryImpl;
import org.myob.repository.Specification;
import org.myob.repository.TaxRepository;
import org.myob.tax.TaxSpecificationImpl;
import org.myob.service.impl.EmployeeServiceImpl;
import org.myob.tax.Tax;
import org.myob.tax.TaxBuilder;
import org.myob.tax.TaxSpecificationBuilder;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeServiceTest {

    private static EmployeeService employeeService;
    private static TaxRepository mockTaxRepository;
    private static EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private static Employee employee;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static EmployeePayslip employeePayslip;

    @BeforeClass
    public static void setup() throws IOException {

        startDate = new LocalDate(2015, 01, 01);
        endDate = new LocalDate(2015, 10, 01);

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

        employeePayslip = employeeService.payslip(startDate, endDate, employee);

    }

    @Test
    public void shouldReturnAPayslipWithTheExpectedFullName() throws IOException {
        assertEquals(employeeBuilder.build().getFullName(), employeePayslip.getEmployeeName());
    }

    @Test
    public void shouldHavePayPeriodFromStartDateToEndDate() {
        assertEquals(EmployeePayslip.formatter.print(startDate) + " " + EmployeePayslip.formatter.print(endDate), employeePayslip.payPeriod());
    }

    @Test
    public void payslipShouldHaveGrossIncomeEqualToAnnualSalaryDividedBy12Months() throws IOException {

        Tax expectedTaxForThisEmployee = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();
        TaxSpecificationImpl expectedCriteriaUsedByRepository = new TaxSpecificationBuilder().withEmployee(employee).build();

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        EmployeePayslip employeePayslip = payslipFactory.createWith(startDate, endDate, employee, expectedTaxForThisEmployee);

        when(mockTaxRepository.find(expectedCriteriaUsedByRepository)).thenReturn(expectedTaxForThisEmployee);

        assertEquals(employeePayslip.getGrossIncome(), employeePayslip.getGrossIncome());
        verify(mockTaxRepository, times(1)).find(Mockito.any(Specification.class));

    }
}
