package repository.impl;

import builder.TaxBuilder;
import domain.Tax;
import reader.Row;
import reader.TaxReader;
import repository.Criteria;
import repository.TaxRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryImpl implements TaxRepository {

    private TaxReader taxReader;

    @Override
    public Tax find(Criteria criteria) throws IOException {
        Row row = null;

        while((row = taxReader.read()) != null) {
            Tax tax = new TaxBuilder().withBaseTax(row.getInt(Row.BASE_TAX))
                    .withMaxIncome(row.getInt(Row.MAX_INCOME))
                    .withMinIncome(row.getInt(Row.MIN_INCOME))
                    .withTaxPerDollar(row.getInt(Row.TAX_PER_DOLLAR)).build();
            if (criteria.match(tax)) {
                return tax;
            }
        }

        return null;
    }

    @Override
    public void setTaxReader(TaxReader taxReader) {
        this.taxReader = taxReader;
    }
}
