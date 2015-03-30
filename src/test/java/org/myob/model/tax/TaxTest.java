package org.myob.model.tax;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxTest {

    private TaxBuilder builder = new TaxBuilder();
    private double expectedMinIncome;
    private double expectedMaxIncome;
    private double expectedTaxPerDollar;
    private double expectedBaseTax;

    @Before
    public void setUp() throws Exception {
        expectedMinIncome = 1000.45;
        expectedMaxIncome = 2700.956;
        expectedTaxPerDollar = 20;
        expectedBaseTax = 3500.89;

    }

    @Test
    public void shouldReturnTaxWithTheExpectedMinIncome(){
        assertEquals(new Double(expectedMinIncome), new Double(builder.withMinIncome(expectedMinIncome).build().getMinIncome()));
    }

    @Test
    public void shouldReturnTaxWithTheExpectedMaxIncome(){
        assertEquals(new Double(expectedMaxIncome), new Double(builder.withMaxIncome(expectedMaxIncome).build().getMaxIncome()));
    }

    @Test
    public void shouldReturnTheExpectedTaxPerDollar(){
        assertEquals(new Double(expectedTaxPerDollar), new Double(builder.withTaxPerDollar(expectedTaxPerDollar).build().getTaxPerDollarInCents()));
    }

    @Test
    public void shouldReturnTheExpectedBaseTax(){
        assertEquals(new Double(expectedBaseTax), new Double(builder.withBaseTax(expectedBaseTax).build().getBaseTax()));
    }

    @Test
    public void shouldReturnFirstJan2015(){
        LocalDate firstJan2015 = new LocalDate(2015,1,1);
        builder.withStartPeriod(firstJan2015.getYear(),firstJan2015.getMonthOfYear(),firstJan2015.getDayOfMonth());
        assertEquals(firstJan2015, builder.build().getStartPeriod());
    }

}
