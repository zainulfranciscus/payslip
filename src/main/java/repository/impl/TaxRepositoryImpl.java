package repository.impl;

import builder.TaxBuilder;
import domain.Tax;
import reader.Row;
import reader.Reader;
import reader.impl.TaxCsvRow;
import repository.Criteria;
import repository.TaxRepository;

import java.io.IOException;
import static reader.TaxHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxRepositoryImpl implements TaxRepository {

    private Reader reader;

    @Override
    public Tax find(Criteria criteria) throws IOException {
        Row row = null;

        while((row = reader.read()) != null) {
            Tax tax = new TaxBuilder().withBaseTax(row.getInt(BASE_TAX))
                    .withMaxIncome(row.getInt(MAX_INCOME))
                    .withMinIncome(row.getInt(MIN_INCOME))
                    .withTaxPerDollar(row.getInt(TAX_PER_DOLLAR)).build();
            if (criteria.match(tax)) {
                return tax;
            }
        }

        return null;
    }

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
