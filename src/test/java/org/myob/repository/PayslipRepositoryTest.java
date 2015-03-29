package org.myob.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.model.tax.Tax;
import org.myob.model.tax.TaxBuilder;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.repository.impl.PayslipRepositoryImpl;
import org.myob.repository.specification.TaxSpecification;
import org.myob.service.PayslipCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipRepositoryTest {

    private PayslipRepository payslipRepository;

    private TaxRepository mockTaxRepository;
    private EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private Payslip payslip;
    private Tax tax;
    private PayslipWriter mockWriter;

    @Before
    public void setup() throws Exception {

        startDate = new LocalDate(2015, 01, 01);
        endDate = new LocalDate(2015, 10, 01);

        employeeBuilder = new EmployeeBuilder();

        employee = employeeBuilder.withFirstName("Joe")
                .withLastName("Blogg")
                .withSalary(12000)
                .withEndPaymentPeriod(2015, 12, 31)
                .withStartPaymentPeriod(2015, 1, 1)
                .build();


        tax = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();

        mockTaxRepository = mock(TaxRepository.class);
        when(mockTaxRepository.find(Mockito.any(TaxSpecification.class))).thenReturn(tax);

        payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(mockTaxRepository);

        mockWriter = mock(PayslipWriter.class);
        payslipRepository.setWriter(mockWriter);

    }


    @Test
    public void shouldCreateAListOfPayslips_With_Name_PayPeriod_GrossIncome_IncomeTax_NetIncome_AndSuper() throws Exception {

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        List<Payslip> payslips = payslipRepository.createPayslips(employees);

        for (Payslip payslip : payslips) {
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
    public void shouldCallMockTaxRepositoryClose1Time() throws Exception {
        PayslipWriter mockWriter = mock(PayslipWriter.class);

        payslipRepository.setWriter(mockWriter);
        payslipRepository.close();

        verify(mockTaxRepository, times(1)).close();
        verify(mockWriter, times(1)).close();
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
        verify(mockWriter, times(1)).write(payslip);
    }

    @Test
    public void payslipWriterShouldBeCalledOnceWhenSavePayslipsIsCalled() throws IOException {

        doNothing().when(mockWriter).write(payslip);
        List<Payslip> payslips = new ArrayList<Payslip>();
        payslips.add(new PayslipBuilder().withName("Joe").withGrossIncome(1000).build());

        payslipRepository.savePayslips(payslips);
        verify(mockWriter, times(1)).write(payslips.get(0));
    }

    @Test
    public void payslipWriterShouldBeCalledOnceWhenWritingHeader() throws IOException {
        doNothing().when(mockWriter).writeHeader();
        payslipRepository.writeHeader();
        verify(mockWriter, times(1)).writeHeader();
    }

    class AssertThat {

        PayslipCalculator payslip;

        AssertThat() {
            payslip = new PayslipCalculator(employee,tax);
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

        AssertThat hasNetIncome(int netIncome) {
            assertEquals(payslip.getNetIncome(), netIncome);
            return this;
        }

        AssertThat hasSuper(int aSuper) {
            assertEquals(payslip.getSuper(), aSuper);
            return this;
        }
    }

}
