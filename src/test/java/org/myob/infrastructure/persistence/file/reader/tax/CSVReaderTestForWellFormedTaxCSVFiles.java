package org.myob.infrastructure.persistence.file.reader.tax;

import org.junit.Test;
import org.myob.infrastructure.persistence.file.reader.AbstractCSVReaderTest;
import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;

import static org.junit.Assert.assertEquals;
import static org.myob.infrastructure.persistence.file.reader.TaxHeader.*;
/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForWellFormedTaxCSVFiles extends AbstractCSVReaderTest {

    @Override
    public Reader readerForCSV() {
        return new TaxCSVReaderImpl("tax/tax.csv");
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

    @Test
    public void taxStartingDateShouldBe1(){
        assertEquals(1,row.getInt(STARTING_DAY));
    }

    @Test
    public void taxStartingMonthShouldBe3(){
        assertEquals(3,row.getInt(STARTING_MONTH));
    }

    @Test
    public void taxStartingYearShouldBe2015(){
        assertEquals(2015, row.getInt(STARTING_YEAR));
    }

}
