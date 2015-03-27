package org.myob.infrastructure.persistence.file.reader;

import org.junit.After;
import org.junit.Test;
import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.myob.infrastructure.persistence.file.reader.TaxHeader.*;
/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvReaderTest {

    private Reader reader;
    private Row row;

    @After
    public void after() throws IOException {
        reader.close();
    }

    @Test
    public void shouldHave0ForMinIncome_MaxIncome_BaseTax_TaxPerDollar() throws IOException {
        reader = new TaxCSVReaderImpl("tax/taxTableWithNonNumericalValues.csv");
        row = reader.read();

        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveBaseTax(0).shouldHaveMaxIncome(0).shouldHaveMinIncome(0).shouldHaveTaxPerDollar(0);
    }

    @Test
    public void shouldHave100ForMinIncome_200ForMaxIncome_300ForBaseTax_400ForTaxPerDollar_1ForStartingDay_3ForStartingMonth_2015ForStartingYear() throws IOException {
        reader = new TaxCSVReaderImpl("tax/tax.csv");
        row = reader.read();

        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveBaseTax(300)
                .shouldHaveMaxIncome(200)
                .shouldHaveMinIncome(100)
                .shouldHaveTaxPerDollar(400)
                .shouldHaveStartingDate(1)
                .shouldHaveStartingMonth(3)
                .shouldHaveStartingYear(2015);
    }

    @Test
    public void rowShouldBeNullBecauseCSVFileOnlyHasHeader() throws IOException {
        reader = new TaxCSVReaderImpl("tax/onlyHaveTaxHeaders.csv");
        assertNull(reader.read());
    }

    @Test
    public void rowShouldBeNullBecauseCSVFileIsEmpty() throws IOException {
        reader = new TaxCSVReaderImpl("emptyFile.csv");
        assertNull(reader.read());
    }

    class AssertThat {

        AssertThat shouldHaveMinIncome(int expectedValue){
            assertEquals(expectedValue,row.getInt(MIN_INCOME));
            return this;
        }

        AssertThat shouldHaveMaxIncome(int expectedValue){
            assertEquals(expectedValue,row.getInt(MAX_INCOME));
            return this;
        }

        AssertThat shouldHaveBaseTax(int expectedValue){
            assertEquals(expectedValue,row.getInt(BASE_TAX));
            return this;
        }

        AssertThat shouldHaveTaxPerDollar(int expectedValue){
            assertEquals(expectedValue,row.getInt(TAX_PER_DOLLAR));
            return this;
        }

        AssertThat shouldHaveStartingDate(int expectedValue){
            assertEquals(expectedValue,row.getInt(STARTING_DAY));
            return this;
        }

        AssertThat shouldHaveStartingMonth(int expectedValue){
            assertEquals(expectedValue,row.getInt(STARTING_MONTH));
            return this;
        }

        AssertThat shouldHaveStartingYear(int expectedValue){
            assertEquals(expectedValue,row.getInt(STARTING_YEAR));
            return this;
        }
    }

}
