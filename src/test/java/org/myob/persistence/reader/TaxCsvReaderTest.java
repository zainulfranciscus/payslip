package org.myob.persistence.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myob.persistence.reader.impl.TaxCSVReaderImpl;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.RowSpecification;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.myob.persistence.mapping.impl.TaxHeader.*;


/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCsvReaderTest {

    private AbstractCsvReader reader;
    private Row row;

    @Before
    public void setup(){
        reader = new TaxCSVReaderImpl();
    }
    @After
    public void after() throws Exception {
        reader.close();
    }

    @Test
    public void shouldHave0ForMinIncome_MaxIncome_BaseTax_TaxPerDollar() throws Exception {


        reader.setFileName(loadFromClassPath("tax/taxTableWithNonNumericalValues.csv"));
        reader.initializeFileReader();

        row = reader.read();

        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveBaseTax(0)
                .shouldHaveMaxIncome(0)
                .shouldHaveMinIncome(0)
                .shouldHaveTaxPerDollar(0);
    }

    @Test
    public void shouldHave100ForMinIncome_200ForMaxIncome_300ForBaseTax_400ForTaxPerDollar_500ForTaxPerDollarOver_1ForStartingDay_3ForStartingMonth_2015ForStartingYear() throws Exception {

        reader.setFileName(loadFromClassPath("tax/tax.csv"));
        reader.initializeFileReader();
        row = reader.read();

        AssertThat assertThat = new AssertThat();
        assertThat.shouldHaveBaseTax(300.87)
                .shouldHaveMaxIncome(200.65)
                .shouldHaveMinIncome(100.76)
                .shouldHaveTaxPerDollar(400.18)
                .shouldHaveTaxPerDollarOver(500.07)
                .shouldHaveStartingDate(1)
                .shouldHaveStartingMonth("March")
                .shouldHaveStartingYear(2015);
    }

    @Test
    public void rowShouldBeNullBecauseCSVFileOnlyHasHeader() throws Exception {

        reader.setFileName(loadFromClassPath("tax/onlyHaveTaxHeaders.csv"));
        reader.initializeFileReader();
        assertNull(reader.read());
    }

    @Test
    public void rowShouldBeNullBecauseCSVFileIsEmpty() throws Exception {

        reader.setFileName(loadFromClassPath("emptyFile.csv"));
        reader.initializeFileReader();
        assertNull(reader.read());
    }

    @Test
    public void rowShouldBeNullBecauseMonthYearAndDate_IsInvalid() throws Exception {

        RowSpecification mockSpecification = mock(RowSpecification.class);
        when(mockSpecification.isValid(row)).thenReturn(false);

        reader.setFileName(loadFromClassPath("tax/taxWithInvalidDates.csv"));
        reader.initializeFileReader();
        reader.setSpecification(mockSpecification);

        assertNull(reader.read());
    }

    @Test
    public void shouldReturnNull_WhenCsvFileDoesNotHaveAllTheRequiredColumns() throws IOException {

        reader.setFileName(loadFromClassPath("tax/taxWithMissingColumns.csv"));
        reader.initializeFileReader();
        assertNull(reader.read());
    }

    @Test
    public void shouldNotReturnNull_BecauseItShouldLoadACsvFileInResourceFolder() throws IOException {
        assertNotNull(reader.csvFileFromClasspath());
    }

    class AssertThat {

        AssertThat shouldHaveMinIncome(double expectedValue){
            assertEquals(new Double(expectedValue),new Double(row.getDouble(MIN_INCOME)));
            return this;
        }

        AssertThat shouldHaveMaxIncome(double expectedValue){
            assertEquals(new Double(expectedValue),new Double(row.getDouble(MAX_INCOME)));
            return this;
        }

        AssertThat shouldHaveBaseTax(double expectedValue){

            assertEquals(new Double(expectedValue),new Double(row.getDouble(BASE_TAX)));
            return this;
        }

        AssertThat shouldHaveTaxPerDollar(double expectedValue){
            assertEquals(new Double(expectedValue),new Double(row.getDouble(TAX_PER_DOLLAR)));
            return this;
        }

        AssertThat shouldHaveStartingDate(int expectedValue){
            assertEquals(expectedValue,row.getInt(STARTING_DAY));
            return this;
        }

        AssertThat shouldHaveStartingMonth(String expectedValue){
            assertEquals(expectedValue,row.get(STARTING_MONTH));
            return this;
        }

        AssertThat shouldHaveStartingYear(int expectedValue){
            assertEquals(expectedValue,row.getInt(STARTING_YEAR));
            return this;
        }

        public AssertThat shouldHaveTaxPerDollarOver(double expectedTaxPerDollarOver) {
            assertEquals(new Double(expectedTaxPerDollarOver),new Double(row.getDouble(TAX_PER_DOLLAR_OVER)));
            return this;
        }
    }

    private String loadFromClassPath(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
