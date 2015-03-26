package builder;

import org.junit.BeforeClass;
import org.junit.Test;
import reader.Row;
import reader.TaxHeader;
import reader.impl.TaxCsvRow;

import static org.junit.Assert.assertEquals;
import static reader.TaxHeader.*;

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

    @BeforeClass
    public static void setup(){
        taxCsvRowBuilder = new TaxCsvRowBuilder();
        expectedMinIncome = "100";
        expectedMaxIncome = "200";
        expectedBaseTax = "300";
        expectedTaxPerDollar = "400";
        row = taxCsvRowBuilder.withMinIncome(expectedMinIncome)
                .withMaxIncome(expectedMaxIncome)
                .withBaseTax(expectedBaseTax)
                .withTaxPerDollar(expectedTaxPerDollar)
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
