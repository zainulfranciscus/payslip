package org.myob.persistence.reader.builder;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.myob.persistence.mapping.impl.TaxHeader;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.builder.TaxCsvRowBuilder;

import static org.junit.Assert.assertEquals;
import static org.myob.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxTaxCsvRowBuilderTest {


    private Row row;
    private String expectedMinIncome;
    private String expectedMaxIncome;
    private String expectedBaseTax;
    private String expectedTaxPerDollar;
    private String expectedStartingYear;
    private String expectedStartingMonth;
    private String expectedStartingDay;
    private int expectedStartingMonthAsInt;
    private TaxCsvRowBuilder taxCsvRowBuilder;

    @Before
    public void setup() {
        taxCsvRowBuilder = new TaxCsvRowBuilder();
        expectedMinIncome = "100";
        expectedMaxIncome = "200";
        expectedBaseTax = "300";
        expectedTaxPerDollar = "400";
        expectedStartingDay = "1";
        expectedStartingMonth = "March";
        expectedStartingMonthAsInt = 3;
        expectedStartingYear = "2015";

        taxCsvRowBuilder = taxCsvRowBuilder.withMinIncome(expectedMinIncome)
                .withMaxIncome(expectedMaxIncome)
                .withBaseTax(expectedBaseTax)
                .withTaxPerDollar(expectedTaxPerDollar)
                .withStartingYear(expectedStartingYear)
                .withStartingMonth(expectedStartingMonth)
                .withStartingDay(expectedStartingDay);

    }

    @Test
    public void shouldHaveTheExpected_MinIncome_MaxIncome_BaseTax_TaxPerDollar() {

        row = taxCsvRowBuilder.build();
        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveTheExpectedBaseTax()
                .shouldHaveTheExpectedMaxIncome()
                .shouldHaveTheExpectedMinIncome()
                .shouldHaveTheExpectedTaxPerDollar()
                .shouldHaveTheExpectedMonth()
                .shouldHaveTheExpectedDate()
                .shouldHaveTheExpectedYear();

    }


    class AssertThat {
        public AssertThat shouldHaveTheExpectedMinIncome() {
            assertEquals(expectedMinIncome, row.get(MIN_INCOME));
            return this;
        }

        public AssertThat shouldHaveTheExpectedMaxIncome() {
            assertEquals(expectedMaxIncome, row.get(MAX_INCOME));
            return this;
        }

        public AssertThat shouldHaveTheExpectedBaseTax() {
            assertEquals(expectedBaseTax, row.get(BASE_TAX));
            return this;
        }

        public AssertThat shouldHaveTheExpectedTaxPerDollar() {
            assertEquals(expectedTaxPerDollar, row.get(TAX_PER_DOLLAR));
            return this;
        }

        public AssertThat shouldHaveTheExpectedMonth() {
            assertEquals(expectedStartingMonthAsInt, row.getMonthAsInt(STARTING_MONTH));
            return this;
        }

        public AssertThat shouldHaveTheExpectedDate() {
            assertEquals(expectedStartingDay, row.get(STARTING_DAY));
            return this;
        }

        public AssertThat shouldHaveTheExpectedYear() {
            assertEquals(expectedStartingYear, row.get(STARTING_YEAR));
            return this;
        }
    }


}
