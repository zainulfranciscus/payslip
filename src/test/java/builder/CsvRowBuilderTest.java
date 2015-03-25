package builder;

import org.junit.BeforeClass;
import org.junit.Test;
import reader.impl.CsvRow;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CsvRowBuilderTest {

    private static CsvRowBuilder csvRowBuilder;
    private static CsvRow row;
    private static int expectedMinIncome;
    private static int expectedMaxIncome;
    private static int expectedBaseTax;
    private static int expectedTaxPerDollar;

    @BeforeClass
    public static void setup(){
        csvRowBuilder = new CsvRowBuilder();
        expectedMinIncome = 100;
        expectedMaxIncome = 200;
        expectedBaseTax = 300;
        expectedTaxPerDollar = 400;
        row = csvRowBuilder.withMinIncome(expectedMinIncome).withMaxIncome(expectedMaxIncome).withBaseTax(expectedBaseTax).withTaxPerDollar(expectedTaxPerDollar).build();
    }

    @Test
    public void shouldBeTheExpectedMinIncome(){
        assertEquals(expectedMinIncome,row.getInt(CsvRow.MIN_INCOME));
    }

    @Test
    public void shouldBeTheExpectedMaxIncome(){
        assertEquals(expectedMaxIncome,row.getInt(CsvRow.MAX_INCOME));
    }

    @Test
    public void shouldBeTheExpectedBaseTax(){
        assertEquals(expectedBaseTax, row.getInt(CsvRow.BASE_TAX));
    }

    @Test
    public void shouldBeTheExpectedTaxPerDollar(){
        assertEquals(expectedTaxPerDollar, row.getInt(CsvRow.TAX_PER_DOLLAR));
    }


}
