package org.accounting.repository.impl;

import org.accounting.model.tax.Tax;
import org.accounting.model.tax.TaxBuilder;
import org.accounting.persistence.reader.Reader;
import org.accounting.persistence.row.Row;
import org.accounting.repository.TaxRepository;
import org.accounting.repository.specification.TaxSpecification;

import static org.accounting.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryImpl implements TaxRepository {

    private Reader reader;

    @Override
    public Tax find(TaxSpecification specification) throws Exception {

        Row row;
        Tax matchingTax = null;

        while((row = reader.read()) != null) {

            Tax tax = new TaxBuilder()
                    .withBaseTax(row.getDouble(BASE_TAX))
                    .withMaxIncome(row.getDouble(MAX_INCOME))
                    .withMinIncome(row.getDouble(MIN_INCOME))
                    .withTaxPerDollar(row.getDouble(TAX_PER_DOLLAR))
                    .withStartPeriod(
                            row.getInt(STARTING_YEAR),
                            row.getMonthAsInt(STARTING_MONTH),
                            row.getInt(STARTING_DAY))
                    .withTaxPerDollarOver(row.getDouble(TAX_PER_DOLLAR_OVER))
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
