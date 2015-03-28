package org.myob.infrastructure.repository.impl;

import org.myob.domain.model.tax.TaxBuilder;
import org.myob.domain.model.tax.Tax;
import org.myob.infrastructure.persistence.file.TaxRowSpecification;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.repository.Specification;
import org.myob.infrastructure.repository.Reader;
import org.myob.infrastructure.repository.TaxRepository;

import static org.myob.infrastructure.persistence.mapping.impl.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryImpl implements TaxRepository {

    private Reader reader;

    @Override
    public Tax find(Specification<Tax> specification) throws Exception {
        Row row = null;
        TaxRowSpecification rowSpecification = new TaxRowSpecification();
        while((row = reader.read(rowSpecification)) != null) {

            Tax tax = new TaxBuilder().withBaseTax(row.getInt(BASE_TAX))
                    .withMaxIncome(row.getInt(MAX_INCOME))
                    .withMinIncome(row.getInt(MIN_INCOME))
                    .withTaxPerDollar(row.getDouble(TAX_PER_DOLLAR))
                    .withStartingDay(row.getInt(STARTING_DAY))
                    .withStartingMonth(row.getMonthAsInt(STARTING_MONTH))
                    .withStartingYear(row.getInt(STARTING_YEAR))
                    .withTaxPerDollarOver(row.getInt(TAX_PER_DOLLAR_OVER))
                    .build();
            if (specification.match(tax)) {
                return tax;
            }
        }
        //reader.close();

        return null;
    }

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
