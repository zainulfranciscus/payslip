package org.myob.reader.builder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.myob.reader.Row;

import static org.junit.Assert.assertEquals;
import static org.myob.reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxTaxCsvRowBuilderTest {

    private static TaxCsvRowBuilder taxCsvRowBuilder;
    private static Row row;
    private static String expectedMinIncome;
    private static String expectedMaxIncome;
    private static String expectedBaseTax;
    private static String expectedTaxPerDollar;
    private static String expectedStartingYear;
    private static String expectedStartingMonth;
    private static String expectedStartingDay;

    @BeforeClass
    public static void setup(){
        taxCsvRowBuilder = new TaxCsvRowBuilder();
        expectedMinIncome = "100";
        expectedMaxIncome = "200";
        expectedBaseTax = "300";
        expectedTaxPerDollar = "400";
        expectedStartingDay = "1";
        expectedStartingMonth = "3";
        expectedStartingYear = "2015";

        row = taxCsvRowBuilder.withMinIncome(expectedMinIncome)
                .withMaxIncome(expectedMaxIncome)
                .withBaseTax(expectedBaseTax)
                .withTaxPerDollar(expectedTaxPerDollar)
                .withStartingYear(expectedStartingYear)
                .withStartingMonth(expectedStartingMonth)
                .withStartingDay(expectedStartingDay)
                .build();
    }

    @Test
    public void shouldBeTheExpectedMinIncome(){
        assertEquals(expectedMinIncome,row.get(MIN_INCOME));
    }

    @Test
    public void shouldBeTheExpectedMaxIncome(){
        assertEquals(expectedMaxIncome,row.get(MAX_INCOME));
    }

    @Test
    public void shouldBeTheExpectedBaseTax(){
        assertEquals(expectedBaseTax, row.get(BASE_TAX));
    }

    @Test
    public void shouldBeTheExpectedTaxPerDollar(){
        assertEquals(expectedTaxPerDollar, row.get(TAX_PER_DOLLAR));
    }


}
