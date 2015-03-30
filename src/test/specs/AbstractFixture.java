import org.myob.persistence.row.builder.EmployeeCsvRowBuilder;
import org.myob.persistence.row.builder.TaxCsvRowBuilder;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.TaxRepositoryImpl;
import reader.ReaderImpl;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public abstract class AbstractFixture {

    protected ReaderImpl taxReader = new ReaderImpl();
    protected ReaderImpl employeeReader = new ReaderImpl();

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
        taxReader.setSpecification(new TaxRowSpecification());


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

        employeeReader.setSpecification(new EmployeeRowSpecification());
        employeeReader.add(employeeCsvRowBuilder.build());
    }

}
