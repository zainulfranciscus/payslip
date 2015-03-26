package reader;

import org.junit.Test;
import reader.impl.TaxCsvRow;

import static org.junit.Assert.assertEquals;
import static reader.TaxHeader.*;
/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForCSVFileWithNonNumericalValues extends AbstractCSVReaderTest{
    @Override
    public String csvFileName() {
        return "taxTableWithNonNumericalValues.csv";
    }

    @Test
    public void minIncomeShouldBe0(){
        valueShouldBe0(row.getInt(MIN_INCOME));
    }

    @Test
    public void maxIncomeShouldBe0(){
        valueShouldBe0(row.getInt(MAX_INCOME));
    }

    @Test
    public void baseTaxShouldBe0(){
        valueShouldBe0(row.getInt(BASE_TAX));
    }

    @Test
    public void taxPerDollarShouldBe0(){
        valueShouldBe0(row.getInt(TAX_PER_DOLLAR));
    }

    private void valueShouldBe0(int value){
        assertEquals(0,value);
    }

}
