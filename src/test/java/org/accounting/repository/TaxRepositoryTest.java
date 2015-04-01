package org.accounting.repository;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.accounting.model.employee.Employee;
import org.accounting.model.employee.EmployeeBuilder;
import org.accounting.persistence.reader.Reader;
import org.accounting.persistence.row.TaxCsvRow;
import org.accounting.repository.impl.TaxRepositoryImpl;
import org.accounting.repository.specification.TaxSpecification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.accounting.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryTest {

    private TaxRepository taxRepository;
    private Reader mockReader;
    private TaxCsvRow mockRow;
    private double baseTax;
    private double maxIncomeForThisTax;
    private double minIncomeForThisTax;
    private double taxPerDollarForThisTax;
    private TaxSpecification taxFor15000AsSalary;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup() throws Exception {

        baseTax = 1000.51;
        maxIncomeForThisTax = 20000.76;
        minIncomeForThisTax = 100.56;
        taxPerDollarForThisTax = 5.2;

        employeeBuilder = new EmployeeBuilder()
                .withEndPaymentPeriod(2015, 12, 31)
                .withStartPaymentPeriod(2015, 1, 1);

        Employee employeeWithSalaryOf15000 = employeeBuilder.withSalary(15000).build();
        taxFor15000AsSalary = new TaxSpecification(employeeWithSalaryOf15000);

        setMockRowBehavior();

        mockReader = mock(Reader.class);
        when(mockReader.read()).thenReturn(mockRow,null);

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
    public void shouldReturnNoMatchingTax_WhenSalaryIsAboveMaxIncomeForThisTax() throws Exception {
        Employee employee = employeeBuilder.withSalary(maxIncomeForThisTax + 2000).build();
        TaxSpecification specification = new TaxSpecification(employee);
        assertNull(taxRepository.find(specification));
    }

    @Test
    public void shouldReturnNoMatchingTax_WhenSalaryIsBelowMinIncomeForThisTax() throws Exception {
        Employee employee = employeeBuilder.withSalary(minIncomeForThisTax - 1000).build();
        TaxSpecification specification = new TaxSpecification(employee);
        assertNull(taxRepository.find(specification));
    }

    @Test
    public void shouldCallMockReaderClose1Time() throws Exception {
        taxRepository.close();
        verify(mockReader,times(1)).close();
    }

    private void setMockRowBehavior(){

        mockRow = mock(TaxCsvRow.class);
        when(mockRow.getDouble(BASE_TAX)).thenReturn(baseTax);
        when(mockRow.getDouble(MAX_INCOME)).thenReturn(maxIncomeForThisTax);
        when(mockRow.getDouble(MIN_INCOME)).thenReturn(minIncomeForThisTax);
        when(mockRow.getDouble(TAX_PER_DOLLAR)).thenReturn(taxPerDollarForThisTax);
        when(mockRow.getInt(STARTING_DAY)).thenReturn(1);
        when(mockRow.getMonthAsInt(STARTING_MONTH)).thenReturn(1);
        when(mockRow.getDate()).thenReturn(new LocalDate());
        when(mockRow.getInt(STARTING_YEAR)).thenReturn(2015);

    }

}
