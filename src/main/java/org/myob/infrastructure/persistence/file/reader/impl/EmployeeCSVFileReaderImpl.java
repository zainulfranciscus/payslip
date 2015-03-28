package org.myob.infrastructure.persistence.file.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.myob.infrastructure.persistence.file.reader.AbstractCsvReader;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.persistence.file.reader.builder.EmployeeCsvRowBuilder;

import static org.myob.infrastructure.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderImpl extends AbstractCsvReader {

    @Override
    public Row make(CSVRecord record) {
        return new EmployeeCsvRowBuilder()
                .withFirstName(record.get(FIRST_NAME.getLabel()))
                .withLastName(record.get(LAST_NAME.getLabel()))
                .withSalary(record.get(ANNUAL_SALARY.getLabel()))
                .withSuper(record.get(SUPER_RATE.getLabel()))
                .withStartOfPaymentDate(record.get(START_PAYMENT_DATE.getLabel()))
                .withStartOfPaymentMonth(record.get(START_PAYMENT_MONTH.getLabel()))
                .withStartOfPaymentYear(record.get(START_PAYMENT_YEAR.getLabel()))
                .withEndOfPaymentDate(record.get(END_PAYMENT_DATE.getLabel()))
                .withEndOfPaymentMonth(record.get(END_PAYMENT_MONTH.getLabel()))
                .withEndOfPaymentYear(record.get(END_PAYMENT_YEAR.getLabel()))
                .build();
    }
}
