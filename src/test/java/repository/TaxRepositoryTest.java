package repository;

import domain.Tax;
import org.junit.Before;
import org.junit.Test;
import reader.Row;
import reader.TaxReader;
import repository.impl.TaxCriteria;
import repository.impl.TaxRepositoryImpl;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryTest {

    private TaxRepository taxRepository;
    private TaxReader mockTaxReader;
    private Row mockRow;
    private Tax tax;
    private int expectedBaseTax;
    private int expectedMaxIncome;
    private int expectedMinIncome;
    private int expectedTaxPerDollar;
    private Criteria taxFor15000AsSalary;

    @Before
    public void setup() throws IOException {

        expectedBaseTax = 1000;
        expectedMaxIncome = 20000;
        expectedMinIncome = 100;
        expectedTaxPerDollar = 50;
        taxFor15000AsSalary = new TaxCriteria(15000);

        setMockRowBehavior();

        mockTaxReader = mock(TaxReader.class);
        when(mockTaxReader.read()).thenReturn(mockRow,null);

        taxRepository = new TaxRepositoryImpl();
        taxRepository.setTaxReader(mockTaxReader);

    }

    @Test
    public void shouldHaveTheExpectedBaseTax() throws IOException {
        assertEquals(expectedBaseTax,taxRepository.find(taxFor15000AsSalary).getBaseTax());
    }

    @Test
    public void shouldHaveTheExpectedBaseMaxIncome() throws IOException {
        assertEquals(expectedMaxIncome, taxRepository.find(taxFor15000AsSalary).getMaxIncome());
    }

    @Test
    public void shouldHaveTheExpectedMinIncome() throws IOException {
        assertEquals(expectedMinIncome, taxRepository.find(taxFor15000AsSalary).getMinIncome());
    }

    @Test
    public void shouldHaveTheExpectedTaxPerDollar() throws IOException {
        assertEquals(expectedTaxPerDollar, taxRepository.find(taxFor15000AsSalary).getTaxPerDollarInCents());
    }

    @Test
    public void shouldBeNullBecauseThereIsNoTaxForThisSalary() throws IOException {
        Criteria aboveMaxIncome = new TaxCriteria(expectedMaxIncome + 1000);
        assertNull(taxRepository.find(aboveMaxIncome));
    }

    private void setMockRowBehavior(){

        mockRow = mock(Row.class);
        when(mockRow.getInt(Row.BASE_TAX)).thenReturn(expectedBaseTax);
        when(mockRow.getInt(Row.MAX_INCOME)).thenReturn(expectedMaxIncome);
        when(mockRow.getInt(Row.MIN_INCOME)).thenReturn(expectedMinIncome);
        when(mockRow.getInt(Row.TAX_PER_DOLLAR)).thenReturn(expectedTaxPerDollar);
    }

}
