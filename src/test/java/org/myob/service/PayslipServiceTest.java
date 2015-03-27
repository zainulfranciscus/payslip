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
import org.myob.service.impl.PayslipServiceImpl;
import org.myob.tax.Tax;
import org.myob.tax.TaxBuilder;
import org.myob.tax.TaxSpecificationBuilder;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class PayslipServiceTest {

    private static PayslipService payslipService;
    private static TaxRepository mockTaxRepository;
    private static EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private static Employee employee;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static EmployeePayslip employeePayslip;
    private static Tax tax;
    private static EmployeePayslip payslipFromPayslipService;

    @BeforeClass
    public static void setup() throws IOException {

        startDate = new LocalDate(2015, 01, 01);
        endDate = new LocalDate(2015, 10, 01);

        employeeBuilder = new EmployeeBuilder();

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


        tax = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        employeePayslip = payslipFactory.createWith(startDate, endDate, employee, tax);

        mockTaxRepository = mock(TaxRepository.class);
        when(mockTaxRepository.find(Mockito.any(Specification.class))).thenReturn(tax);

        payslipService = new PayslipServiceImpl();
        payslipService.setTaxRepository(mockTaxRepository);
        payslipFromPayslipService =  payslipService.payslip(startDate, endDate, employee);

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
    public void grossIncomeShouldBeEmployeePayslipGrossIncome() throws IOException {
        assertEquals(employeePayslip.getGrossIncome(), payslipFromPayslipService.getGrossIncome());
    }

    @Test
    public void incomeTaxShouldBeEmployeePayslipIncomeTax(){
        assertEquals(employeePayslip.getIncomeTax(), payslipFromPayslipService.getIncomeTax());
    }

    @Test
    public void netIncomeShouldBeEmployeePayslipNetIncome(){
        assertEquals(employeePayslip.netIncome(),payslipFromPayslipService.netIncome());
    }

    @Test
    public void superShouldBeEmployeePayslipSuper(){
        assertEquals(employeePayslip.getSuper(),payslipFromPayslipService.getSuper());
    }
}
