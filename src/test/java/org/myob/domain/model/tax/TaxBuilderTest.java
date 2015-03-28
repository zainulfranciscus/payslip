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
    private int expectedMinIncome;
    private int expectedMaxIncome;
    private double expectedTaxPerDollar;
    private int expectedBaseTax;
    private int startingDay;
    private int startingMonth;
    private int startingYear;

    @Before
    public void setUp() throws Exception {
        expectedMinIncome = 1000;
        expectedMaxIncome = 2000;
        expectedTaxPerDollar = 20;
        expectedBaseTax = 3000;

        LocalDate today = new LocalDate();

        startingDay = today.getDayOfMonth();
        startingMonth = today.getMonthOfYear();
        startingYear = today.getYear();
    }

    @Test
    public void shouldReturnTaxWithMinIncomeOf1000(){
        assertEquals(new Double(expectedMinIncome), new Double(builder.withMinIncome(expectedMinIncome).build().getMinIncome()));
    }

    @Test
    public void shouldReturnTaxWithMaxIncomeOf2000(){
        assertEquals(new Double(expectedMaxIncome), new Double(builder.withMaxIncome(expectedMaxIncome).build().getMaxIncome()));
    }

    @Test
    public void shouldReturn20AsTaxPerDollar(){
        assertEquals(new Double(expectedTaxPerDollar), new Double(builder.withTaxPerDollar(expectedTaxPerDollar).build().getTaxPerDollarInCents()));
    }

    @Test
    public void shouldReturn3000AsBaseTax(){
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
