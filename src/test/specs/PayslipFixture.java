import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.service.builder.PayslipServiceBuilder;
import org.myob.repository.specification.EmployeeSpecificationBuilder;
import org.myob.model.payslip.Payslip;
import org.myob.service.PayslipService;
import org.myob.persistence.row.builder.EmployeeCsvRowBuilder;
import org.myob.persistence.row.builder.TaxCsvRowBuilder;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.TaxRepositoryImpl;
import reader.ReaderImpl;
import service.PayslipServiceBuilderImpl;

import java.util.List;

@RunWith(ConcordionRunner.class)
public class PayslipFixture {

    private ReaderImpl taxReader = new ReaderImpl();
    private ReaderImpl employeeReader = new ReaderImpl();

    public void setUpTax(String minIncome,
                         String maxIncome,
                         String baseTax,
                         String taxPerDollar,
                         String taxPerDollarOver,
                         String date,
                         String month,
                         String year) {


        TaxCsvRowBuilder taxCsvRowBuilder = new TaxCsvRowBuilder();
        taxCsvRowBuilder.withBaseTax(baseTax);
        taxCsvRowBuilder.withMaxIncome(maxIncome);
        taxCsvRowBuilder.withMinIncome(minIncome);
        taxCsvRowBuilder.withStartingDay(date);
        taxCsvRowBuilder.withStartingMonth(month);
        taxCsvRowBuilder.withStartingYear(year);
        taxCsvRowBuilder.withTaxPerDollar(taxPerDollar);
        taxCsvRowBuilder.withTaxPerDollarOver(taxPerDollarOver);


        taxReader.add(taxCsvRowBuilder.build());

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(taxReader);

    }

    public void setUpEmployee(String firstName,
                              String lastName,
                              String annualSalary,
                              String superRate,
                              String paymentStartDate,
                              String paymentStartMonth,
                              String paymentStartYear,
                              String paymentEndDate,
                              String paymentEndMonth,
                              String paymentEndYear) {


        EmployeeCsvRowBuilder employeeCsvRowBuilder = new EmployeeCsvRowBuilder();
        employeeCsvRowBuilder.withEndOfPaymentDate(paymentEndDate)
                .withEndOfPaymentMonth(paymentEndMonth)
                .withEndOfPaymentYear(paymentEndYear)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withStartOfPaymentDate(paymentStartDate)
                .withStartOfPaymentMonth(paymentStartMonth)
                .withStartOfPaymentYear(paymentStartYear)
                .withSalary(annualSalary)
                .withSuper(superRate);

        employeeReader.add(employeeCsvRowBuilder.build());
    }

    public List<Payslip> payslip() throws Exception {

        PayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilderImpl();
        payslipServiceBuilder.setReaderForEmployeeRepository(employeeReader);
        payslipServiceBuilder.setReaderForTaxRepository(taxReader);
        PayslipService payslipService = payslipServiceBuilder.build();

        return payslipService.createPayslips(payslipServiceBuilder.createEmployeeRepository().find(new EmployeeSpecificationBuilder().build()));
    }

    public void cleanUpData() {
        taxReader = new ReaderImpl();
        cleanUpEmployeeData();
    }

    public void cleanUpEmployeeData() {
        employeeReader = new ReaderImpl();
    }


}