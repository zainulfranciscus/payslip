package org.myob.repository.impl;

import org.myob.model.tax.Tax;
import org.myob.model.tax.TaxBuilder;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.repository.TaxRepository;
import org.myob.repository.specification.TaxSpecification;

import static org.myob.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryImpl implements TaxRepository {

    private Reader reader;

    @Override
    public Tax find(TaxSpecification specification) throws Exception {

        Row row;
        Tax matchingTax = null;
        TaxRowSpecification rowSpecification = new TaxRowSpecification();
        while((row = reader.read(rowSpecification)) != null) {

            Tax tax = new TaxBuilder().withBaseTax(row.getInt(BASE_TAX))
                    .withMaxIncome(row.getInt(MAX_INCOME))
                    .withMinIncome(row.getInt(MIN_INCOME))
                    .withTaxPerDollar(row.getDouble(TAX_PER_DOLLAR))
                    .withStartPeriod(
                            row.getInt(STARTING_YEAR),
                            row.getMonthAsInt(STARTING_MONTH),
                            row.getInt(STARTING_DAY))
                    .withTaxPerDollarOver(row.getInt(TAX_PER_DOLLAR_OVER))
                    .build();
            if (specification.match(tax)) {
                matchingTax = tax;
                break;
            }
        }

        reader.initializeFileReader();
        return matchingTax;
    }

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
