package reader;

import org.junit.Test;
import reader.impl.TaxCsvRow;

import static org.junit.Assert.assertEquals;
import static reader.TaxHeader.*;
/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForWellFormedCSVFiles extends AbstractCSVReaderTest{

    @Override
    public String csvFileName() {
        return "tax.csv";
    }

    @Test
    public void minIncomeShouldBe100(){
        assertEquals(100,row.getInt(MIN_INCOME));
    }

    @Test
    public void maxIncomeShouldBe200(){
        assertEquals(200,row.getInt(MAX_INCOME));
    }

    @Test
    public void baseTaxShouldBe300(){
        assertEquals(300,row.getInt(BASE_TAX));
    }

    @Test
    public void taxPerDollarShouldBe400(){
        assertEquals(400,row.getInt(TAX_PER_DOLLAR));
    }

}
