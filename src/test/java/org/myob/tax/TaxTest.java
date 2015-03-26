package org.myob.tax;

import org.myob.tax.TaxBuilder;
import org.junit.Before;
import org.junit.Test;
import org.myob.tax.Tax;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus  on 25/03/2015.
 */
public class TaxTest {

    private Tax tax;
    private int expectedBaseTax;
    private int expectedTaxPerDollar;
    private int expectedMaxIncome;
    private int expectedMinIncome;

    @Before
    public void setup(){
        expectedBaseTax = 10;
        expectedTaxPerDollar = 20;
        expectedMaxIncome = 30;
        expectedMinIncome = 5;
        tax = new TaxBuilder().withBaseTax(expectedBaseTax).withTaxPerDollar(expectedTaxPerDollar).withMaxIncome(expectedMaxIncome).withMinIncome(expectedMinIncome).build();
    }

    @Test
    public void shouldReturn10AsBaseTax(){
        assertEquals(expectedBaseTax,tax.getBaseTax());
    }

    @Test
    public void shouldReturn20AsTaxPerDollar(){
        assertEquals(expectedTaxPerDollar, tax.getTaxPerDollarInCents());
    }

    @Test
    public void shouldReturn30AsMaxIncome(){
        assertEquals(expectedMaxIncome,tax.getMaxIncome());
    }

    @Test
    public void shouldReturn5AsMinIncome(){
        assertEquals(expectedMinIncome, tax.getMinIncome());
    }
}
