package builder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class TaxBuilderTest {

    private TaxBuilder builder = new TaxBuilder();
    private int expectedMinIncome;
    private int expectedMaxIncome;
    private int expectedTaxPerDollar;
    private int expectedBaseTax;

    @Before
    public void setUp() throws Exception {
        expectedMinIncome = 1000;
        expectedMaxIncome = 2000;
        expectedTaxPerDollar = 20;
        expectedBaseTax = 3000;
    }

    @Test
    public void shouldReturnTaxWithMinIncomeOf1000(){
        assertEquals(expectedMinIncome, builder.withMinIncome(expectedMinIncome).build().getMinIncome());
    }

    @Test
    public void shouldReturnTaxWithMaxIncomeOf2000(){
        assertEquals(expectedMaxIncome, builder.withMaxIncome(expectedMaxIncome).build().getMaxIncome());
    }

    @Test
    public void shouldReturn20AsTaxPerDollar(){
        assertEquals(expectedTaxPerDollar, builder.withTaxPerDollar(expectedTaxPerDollar).build().getTaxPerDollarInCents());
    }

    @Test
    public void shouldReturn3000AsBaseTax(){
        assertEquals(expectedBaseTax, builder.withBaseTax(expectedBaseTax).build().getBaseTax());
    }

}
