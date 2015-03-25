package reader;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import reader.impl.CSVReaderImpl;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
        assertEquals(100,row.getInt(Row.MIN_INCOME));
    }

    @Test
    public void maxIncomeShouldBe200(){
        assertEquals(200,row.getInt(Row.MAX_INCOME));
    }

    @Test
    public void baseTaxShouldBe300(){
        assertEquals(300,row.getInt(Row.BASE_TAX));
    }

    @Test
    public void taxPerDollarShouldBe400(){
        assertEquals(400,row.getInt(Row.TAX_PER_DOLLAR));
    }

}
