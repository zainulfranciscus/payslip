package org.myob.repository;

import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.myob.reader.Row;
import org.myob.repository.impl.TaxRepositoryImpl;
import org.myob.tax.TaxSpecificationBuilder;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.myob.reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryTest {

    private TaxRepository taxRepository;
    private Reader mockReader;
    private Row mockRow;
    private int baseTax;
    private int maxIncomeForThisTax;
    private int minIncomeForThisTax;
    private int taxPerDollarForThisTax;
    private Specification taxFor15000AsSalary;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup() throws IOException {

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
        when(mockReader.read()).thenReturn(mockRow,null);

        taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(mockReader);

    }

    @Test
    public void shouldHaveTheBaseTax() throws IOException {
        assertEquals(baseTax,taxRepository.find(taxFor15000AsSalary).getBaseTax());
    }

    @Test
    public void shouldBeMaxIncomeForThisTax() throws IOException {
        assertEquals(maxIncomeForThisTax, taxRepository.find(taxFor15000AsSalary).getMaxIncome());
    }

    @Test
    public void shouldBeMinIncomeForThisTax() throws IOException {
        assertEquals(minIncomeForThisTax, taxRepository.find(taxFor15000AsSalary).getMinIncome());
    }

    @Test
    public void shouldBeTaxPerDollarForThisTax() throws IOException {
        assertEquals(taxPerDollarForThisTax, taxRepository.find(taxFor15000AsSalary).getTaxPerDollarInCents());
    }

    @Test
    public void shouldBeNullWhenSalaryIsAboveMaxIncomeForThisTax() throws IOException {
        Employee employee = employeeBuilder.withSalary(maxIncomeForThisTax + 2000).build();
        Specification aboveMaxIncome = new TaxSpecificationBuilder().withEmployee(employee).build();
        assertNull(taxRepository.find(aboveMaxIncome));
    }

    private void setMockRowBehavior(){

        mockRow = mock(Row.class);
        when(mockRow.getInt(BASE_TAX)).thenReturn(baseTax);
        when(mockRow.getInt(MAX_INCOME)).thenReturn(maxIncomeForThisTax);
        when(mockRow.getInt(MIN_INCOME)).thenReturn(minIncomeForThisTax);
        when(mockRow.getInt(TAX_PER_DOLLAR)).thenReturn(taxPerDollarForThisTax);
        when(mockRow.getInt(STARTING_DAY)).thenReturn(01);
        when(mockRow.getInt(STARTING_MONTH)).thenReturn(01);
        when(mockRow.getInt(STARTING_YEAR)).thenReturn(2015);

    }

}
