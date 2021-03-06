import org.accounting.persistence.row.builder.EmployeeCsvRowBuilder;
import org.accounting.persistence.row.builder.TaxCsvRowBuilder;
import org.accounting.persistence.row.specification.impl.EmployeeRowSpecification;
import org.accounting.persistence.row.specification.impl.TaxRowSpecification;
import reader.InMemoryReader;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public abstract class AbstractFixture {

    protected InMemoryReader taxReader = new InMemoryReader();
    protected InMemoryReader employeeReader = new InMemoryReader();

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
