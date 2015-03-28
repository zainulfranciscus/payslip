package org.myob.domain.model.tax;

import org.myob.domain.model.tax.TaxBuilder;
import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.tax.Tax;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus  on 25/03/2015.
 */
public class TaxTest {

    private Tax tax;
    private double expectedBaseTax;
    private double expectedTaxPerDollar;
    private double expectedMaxIncome;
    private double expectedMinIncome;
    private double expectedTaxPerDollarOver;

    @Before
    public void setup(){
        expectedBaseTax = 10.55;
        expectedTaxPerDollar = 20.87;
        expectedMaxIncome = 30.75;
        expectedMinIncome = 5.5;
        expectedTaxPerDollarOver = 7.8;
        tax = new TaxBuilder().withBaseTax(expectedBaseTax)
                .withTaxPerDollar(expectedTaxPerDollar)
                .withMaxIncome(expectedMaxIncome)
                .withMinIncome(expectedMinIncome)
                .withTaxPerDollarOver(expectedTaxPerDollarOver)
                .build();
    }

    @Test
    public void shouldReturnTheExpectedBaseTaxAsBaseTax(){
        assertEquals(new Double(expectedBaseTax),new Double(tax.getBaseTax()));
    }

    @Test
    public void shouldReturnTheExpectedTaxPerDollarAsTaxPerDollar(){
        assertEquals(new Double(expectedTaxPerDollar), new Double(tax.getTaxPerDollarInCents()));
    }

    @Test
    public void shouldReturnTheExpectedMaxIncomeAsMaxIncome(){
        assertEquals(new Double(expectedMaxIncome),new Double(tax.getMaxIncome()));
    }

    @Test
    public void shouldReturnTheExpectedMinIncomeAsMinIncome(){
        assertEquals(new Double(expectedMinIncome), new Double(tax.getMinIncome()));
    }
}
