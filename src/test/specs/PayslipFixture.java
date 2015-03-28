import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.domain.model.employee.EmployeeSpecificationBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.service.PayslipService;
import org.myob.infrastructure.persistence.file.reader.builder.EmployeeCsvRowBuilder;
import org.myob.infrastructure.persistence.file.reader.builder.TaxCsvRowBuilder;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.impl.EmployeeRepositoryImpl;
import org.myob.infrastructure.repository.impl.PayslipRepositoryImpl;
import org.myob.infrastructure.repository.impl.TaxRepositoryImpl;
import org.myob.infrastructure.service.EmployeeRepository;
import org.myob.infrastructure.service.PayslipRepository;
import org.myob.infrastructure.service.PayslipServiceImpl;
import reader.ReaderImpl;

import java.util.List;

@RunWith(ConcordionRunner.class)
public class PayslipFixture {

    private ReaderImpl taxReader;
    private ReaderImpl employeeReader;

    public void setUpTax(String minIncome,
                         String maxIncome,
                         String baseTax,
                         String taxPerDollar,
                         String taxPerDollarOver,
                         String date,
                         String month,
                         String year){

        taxReader = new ReaderImpl();
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
                              String paymentEndYear){

        employeeReader = new ReaderImpl();
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

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(employeeReader);

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(taxReader);

        PayslipRepository payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(taxRepository);

        PayslipService payslipService = new PayslipServiceImpl();
        payslipService.setEmployeeRepository(employeeRepository);
        payslipService.setPayslipRepository(payslipRepository);

        return payslipService.createPayslips(employeeRepository.find(new EmployeeSpecificationBuilder().build()));
    }

}