package org.accounting.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.accounting.model.employee.Employee;
import org.accounting.model.employee.EmployeeBuilder;
import org.accounting.model.payslip.Payslip;
import org.accounting.model.payslip.PayslipBuilder;
import org.accounting.model.tax.Tax;
import org.accounting.model.tax.TaxBuilder;
import org.accounting.persistence.writer.PayslipWriter;
import org.accounting.repository.impl.PayslipRepositoryImpl;
import org.accounting.repository.specification.TaxSpecification;

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
    private Payslip payslip;
    private Tax tax;
    private PayslipWriter mockWriter;

    @Before
    public void setup() throws Exception {

        employeeBuilder = new EmployeeBuilder();

        employee = employeeBuilder
                .withFirstName("Joe")
                .withLastName("Blogg")
                .withSalary(12000)
                .withEndPaymentPeriod(2015, 12, 31)
                .withStartPaymentPeriod(2015, 1, 1)
                .build();

        tax = new TaxBuilder()
                .withMaxIncome(20000)
                .withMinIncome(10)
                .withBaseTax(2500)
                .build();

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
    public void shouldCallMockTaxRepository_Close1Time() throws Exception {
        PayslipWriter mockWriter = mock(PayslipWriter.class);

        payslipRepository.setWriter(mockWriter);
        payslipRepository.close();

        verify(mockTaxRepository, times(1)).close();
        verify(mockWriter, times(1)).close();
    }

    @Test
    public void payslipShouldHaveTheRight_Name_PayPeriod_GrossIncome_IncomeTax_NetIncome_AndSuper() throws Exception {

        payslip = payslipRepository.createPayslipForThisEmployee(employee);

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
