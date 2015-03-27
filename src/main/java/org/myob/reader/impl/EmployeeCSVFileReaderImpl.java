package org.myob.reader.impl;

import org.myob.reader.builder.EmployeeCsvRowBuilder;
import org.apache.commons.csv.CSVRecord;
import org.myob.reader.Row;
import org.myob.reader.AbstractCsvReader;

import static org.myob.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderImpl extends AbstractCsvReader {
    public EmployeeCSVFileReaderImpl(String fileName) {
        super(fileName);
    }

    @Override
    public Row make(CSVRecord record) {
        return new EmployeeCsvRowBuilder().withFirstName(record.get(FIRST_NAME.getLabel()))
                .withLastName(record.get(LAST_NAME.getLabel()))
                .withPaymentDate(record.get(PAYMENT_DATE.getLabel()))
                .withSalary(record.get(ANNUAL_SALARY.getLabel()))
                .withSuper(record.get(SUPER_RATE.getLabel()))
                .build();
    }
}
