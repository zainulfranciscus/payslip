package org.accounting.persistence;

import org.junit.Test;
import org.accounting.persistence.row.specification.impl.TaxRowSpecification;
import org.accounting.persistence.row.builder.TaxCsvRowBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class TaxCsvRowSpecificationTest {

    private TaxCsvRowBuilder taxCsvRowBuilder = new TaxCsvRowBuilder();
    private TaxRowSpecification rowSpecification = new TaxRowSpecification();

    @Test
    public void shouldBeFalse_BecauseMonthIsInvalid() {
        taxCsvRowBuilder.withStartingMonth("13");
        assertFalse(rowSpecification.isValid(taxCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecauseDateIsInvalid() {
        taxCsvRowBuilder.withStartingDay("32");
        assertFalse(rowSpecification.isValid(taxCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeFalse_BecauseYearIsInvalid() {
        taxCsvRowBuilder.withStartingDay("2000 A.D");
        assertFalse(rowSpecification.isValid(taxCsvRowBuilder.build()));
    }

    @Test
    public void shouldBeTrue_BecauseDay_Month_Year_IsValid() {
        taxCsvRowBuilder.withStartingDay("1")
                .withStartingMonth("October")
                .withStartingYear("2015");
        assertTrue(rowSpecification.isValid(taxCsvRowBuilder.build()));
    }
}
