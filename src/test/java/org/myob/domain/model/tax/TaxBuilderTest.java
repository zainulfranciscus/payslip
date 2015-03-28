package org.myob.domain.model.tax;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.tax.TaxBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilderTest {

    private TaxBuilder builder = new TaxBuilder();
    private double expectedMinIncome;
    private double expectedMaxIncome;
    private double expectedTaxPerDollar;
    private double expectedBaseTax;
    private int startingDay;
    private int startingMonth;
    private int startingYear;

    @Before
    public void setUp() throws Exception {
        expectedMinIncome = 1000.45;
        expectedMaxIncome = 2700.956;
        expectedTaxPerDollar = 20;
        expectedBaseTax = 3500.89;

        LocalDate today = new LocalDate();

        startingDay = today.getDayOfMonth();
        startingMonth = today.getMonthOfYear();
        startingYear = today.getYear();
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
    public void shouldReturnStartingDay(){
        assertEquals(startingDay,builder.withStartingDay(startingDay).build().getStartingDay());
    }

    @Test
    public void shouldReturnStartingMonth(){
        assertEquals(startingMonth,builder.withStartingMonth(startingMonth).build().getStartingMonth());
    }

    @Test
    public void shouldReturnStartingYear(){
        assertEquals(startingYear,builder.withStartingYear(startingYear).build().getStartingYear());
    }

}
