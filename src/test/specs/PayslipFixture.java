import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.application.service.PayslipServiceBuilder;
import org.myob.domain.model.employee.EmployeeSpecificationBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.service.PayslipService;
import org.myob.infrastructure.persistence.file.reader.builder.EmployeeCsvRowBuilder;
import org.myob.infrastructure.persistence.file.reader.builder.TaxCsvRowBuilder;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.impl.TaxRepositoryImpl;
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