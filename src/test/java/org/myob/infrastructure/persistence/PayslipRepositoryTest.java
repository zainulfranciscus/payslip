package org.myob.infrastructure.persistence;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.employee.PayslipFactory;
import org.myob.domain.model.payslip.PayslipFactoryImpl;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.domain.model.tax.TaxRepository;
import org.myob.domain.model.tax.TaxSpecificationBuilder;
import org.myob.service.PayslipWriter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipRepositoryTest {

    private static PayslipRepository payslipRepository;

    private static TaxRepository mockTaxRepository;
    private static EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private static Employee employee;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static Payslip payslip;
    private static Tax tax;

    @BeforeClass
    public static void setup() throws IOException {

        startDate = new LocalDate(2015, 01, 01);
        endDate = new LocalDate(2015, 10, 01);

        employeeBuilder = new EmployeeBuilder();

        employee = employeeBuilder.withFirstName("Joe")
                .withLastName("Blogg")
                .withSalary(12000)
                .withEndOfPaymentDate(endDate.getDayOfMonth())
                .withEndOfPaymentMonth(endDate.getMonthOfYear())
                .withEndOfPaymentYear(endDate.getYear())
                .withStartOfPaymentDate(startDate.getDayOfMonth())
                .withStartOfPaymentMonth(startDate.getMonthOfYear())
                .withStartOfPaymentYear(startDate.getYear())
                .build();


        tax = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();

        mockTaxRepository = mock(TaxRepository.class);
        when(mockTaxRepository.find(Mockito.any(Specification.class))).thenReturn(tax);

        payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(mockTaxRepository);

        payslip = payslipRepository.find(new TaxSpecificationBuilder().withEmployee(employee).build());

    }

    @Test
    public void payslipShouldHaveTheRight_Name_PayPeriod_GrossIncome_IncomeTax_NetIncome_AndSuper() throws IOException {
        AssertThat assertThat = new AssertThat();
        assertThat.hasGrossIncome(payslip.getGrossIncome())
                .hasIncomeTax(payslip.getIncomeTax())
                .hasName(payslip.getEmployeeName())
                .hasNetIncome(payslip.netIncome())
                .hasPayPeriod(payslip.payPeriod())
                .hasSuper(payslip.getSuper());
    }

    @Test
    public void shouldCallThePayslipWriterWithAPayslip() throws IOException {
        PayslipWriter mockWriter = mock(PayslipWriter.class);

        doNothing().when(mockWriter).write(payslip);
        payslipRepository.setWriter(mockWriter);

        payslipRepository.save(payslip);
        verify(mockWriter,times(1)).write(payslip);
    }

    class AssertThat {

        Payslip payslip;

        AssertThat() {

            PayslipFactory payslipFactory = new PayslipFactoryImpl();
            payslip = payslipFactory.createWith(startDate, endDate, employee, tax);
        }

        AssertThat hasName(String name) {

            assertEquals(payslip.getEmployeeName(), name);
            return this;
        }

        AssertThat hasPayPeriod(String payPeriod) {
            assertEquals(payslip.payPeriod(), payPeriod);
            return this;
        }

        AssertThat hasGrossIncome(int grossIncome) {
            assertEquals(payslip.getGrossIncome(), grossIncome);
            return this;
        }

        AssertThat hasIncomeTax(int incomeTax) {
            assertEquals(payslip.getIncomeTax(), incomeTax);
            return this;
        }

        AssertThat hasNetIncome(int netIncome){
            assertEquals(payslip.netIncome(),netIncome);
            return this;
        }

        AssertThat hasSuper(int aSuper){
            assertEquals(payslip.getSuper(),aSuper);
            return this;
        }
    }

}
