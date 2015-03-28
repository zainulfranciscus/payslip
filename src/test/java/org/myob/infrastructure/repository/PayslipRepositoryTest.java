package org.myob.infrastructure.repository;

import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.infrastructure.repository.impl.PayslipRepositoryImpl;
import org.myob.domain.model.payslip.PayslipFactoryImpl;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.infrastructure.service.PayslipRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static void setup() throws Exception {

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

    }


    @Test
    public void shouldCreateAListOfPayslips_With_Name_PayPeriod_GrossIncome_IncomeTax_NetIncome_AndSuper() throws Exception {

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        List<Payslip> payslips = payslipRepository.createPayslips(employees);

        for(Payslip payslip: payslips){
            AssertThat assertThat = new AssertThat();
            assertThat.hasGrossIncome(payslip.getGrossIncome())
                    .hasIncomeTax(payslip.getIncomeTax())
                    .hasName(payslip.getEmployeeName())
                    .hasNetIncome(payslip.getNetIncome())
                    .hasPayPeriod(payslip.getPayPeriod())
                    .hasSuper(payslip.getSuper());
        }
    }

    @Test
    public void payslipShouldHaveTheRight_Name_PayPeriod_GrossIncome_IncomeTax_NetIncome_AndSuper() throws Exception {

        payslip = payslipRepository.create(employee);

        AssertThat assertThat = new AssertThat();
        assertThat.hasGrossIncome(payslip.getGrossIncome())
                .hasIncomeTax(payslip.getIncomeTax())
                .hasName(payslip.getEmployeeName())
                .hasNetIncome(payslip.getNetIncome())
                .hasPayPeriod(payslip.getPayPeriod())
                .hasSuper(payslip.getSuper());
    }

    @Test
     public void payslipWriterShouldBeCalledWhenSaveIsCalled() throws IOException {
        PayslipWriter mockWriter = mock(PayslipWriter.class);

        doNothing().when(mockWriter).write(payslip);
        payslipRepository.setWriter(mockWriter);

        payslipRepository.save(payslip);
        verify(mockWriter,times(1)).write(payslip);
    }

    @Test
    public void payslipWriterShouldBeCalledWhenSavePayslipsIsCalled() throws IOException {
        PayslipWriter mockWriter = mock(PayslipWriter.class);

        doNothing().when(mockWriter).write(payslip);
        payslipRepository.setWriter(mockWriter);

        List<Payslip> payslips = new ArrayList<Payslip>();
        payslips.add(new PayslipFactoryImpl().createWith(startDate,endDate,employee,tax));

        payslipRepository.savePayslips(payslips);
        verify(mockWriter,times(1)).write(payslips.get(0));
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
            assertEquals(payslip.getPayPeriod(), payPeriod);
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
            assertEquals(payslip.getNetIncome(),netIncome);
            return this;
        }

        AssertThat hasSuper(int aSuper){
            assertEquals(payslip.getSuper(),aSuper);
            return this;
        }
    }

}
