package org.myob.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.model.tax.Tax;
import org.myob.persistence.mapping.impl.TaxHeader;
import org.myob.repository.specification.TaxSpecificationBuilder;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.persistence.row.TaxCsvRow;
import org.myob.persistence.reader.Reader;
import org.myob.repository.specification.Specification;
import org.myob.repository.impl.TaxRepositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.myob.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryTest {

    private TaxRepository taxRepository;
    private Reader mockReader;
    private TaxCsvRow mockRow;
    private int baseTax;
    private int maxIncomeForThisTax;
    private int minIncomeForThisTax;
    private double taxPerDollarForThisTax;
    private Specification<Tax> taxFor15000AsSalary;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup() throws Exception {

        baseTax = 1000;
        maxIncomeForThisTax = 20000;
        minIncomeForThisTax = 100;
        taxPerDollarForThisTax = 50;

        employeeBuilder = new EmployeeBuilder().withStartOfPaymentDate(1)
                .withStartOfPaymentMonth(1)
                .withStartOfPaymentYear(2015)
                .withEndOfPaymentDate(01)
                .withEndOfPaymentMonth(12)
                .withEndOfPaymentYear(2015);

        Employee employee = employeeBuilder.withSalary(15000).build();

        taxFor15000AsSalary = new TaxSpecificationBuilder().withEmployee(employee).build();

        setMockRowBehavior();

        mockReader = mock(Reader.class);
        when(mockReader.read(Mockito.isA(TaxRowSpecification.class))).thenReturn(mockRow,null);

        taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(mockReader);

    }

    @Test
    public void shouldHaveTheBaseTax() throws Exception {
        assertEquals(new Double(baseTax),new Double(taxRepository.find(taxFor15000AsSalary).getBaseTax()));
    }

    @Test
    public void shouldBeMaxIncomeForThisTax() throws Exception {
        assertEquals(new Double(maxIncomeForThisTax), new Double(taxRepository.find(taxFor15000AsSalary).getMaxIncome()));
    }

    @Test
    public void shouldBeMinIncomeForThisTax() throws Exception {
        assertEquals(new Double(minIncomeForThisTax), new Double(taxRepository.find(taxFor15000AsSalary).getMinIncome()));
    }

    @Test
    public void shouldBeTaxPerDollarForThisTax() throws Exception {
        assertEquals(new Double(taxPerDollarForThisTax), new Double(taxRepository.find(taxFor15000AsSalary).getTaxPerDollarInCents()));
    }

    @Test
    public void shouldBeNullWhenSalaryIsAboveMaxIncomeForThisTax() throws Exception {
        Employee employee = employeeBuilder.withSalary(maxIncomeForThisTax + 2000).build();
        Specification aboveMaxIncome = new TaxSpecificationBuilder().withEmployee(employee).build();
        assertNull(taxRepository.find(aboveMaxIncome));
    }

    @Test
    public void shouldReturnNullTax_BecauseMonthIsAnInvalidCalendarMonth() throws Exception {
        when(mockRow.getMonthAsInt(STARTING_MONTH)).thenReturn(13);
        assertNull(taxRepository.find(taxFor15000AsSalary));
    }

    private void setMockRowBehavior(){

        mockRow = mock(TaxCsvRow.class);
        when(mockRow.getInt(BASE_TAX)).thenReturn(baseTax);
        when(mockRow.getInt(MAX_INCOME)).thenReturn(maxIncomeForThisTax);
        when(mockRow.getInt(MIN_INCOME)).thenReturn(minIncomeForThisTax);
        when(mockRow.getDouble(TAX_PER_DOLLAR)).thenReturn(taxPerDollarForThisTax);
        when(mockRow.getInt(STARTING_DAY)).thenReturn(1);
        when(mockRow.getMonthAsInt(STARTING_MONTH)).thenReturn(1);
        when(mockRow.getDate()).thenReturn(new LocalDate());
        when(mockRow.getInt(STARTING_YEAR)).thenReturn(2015);

    }

}
