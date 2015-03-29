package org.myob.persistence.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.myob.persistence.reader.AbstractCsvReader;
import org.myob.persistence.row.Row;
import org.myob.persistence.row.builder.EmployeeCsvRowBuilder;

import java.io.InputStreamReader;

import static org.myob.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderImpl extends AbstractCsvReader {

    private static final String DEFAULT_EMPLOYEE_CSV_FILE = "sample_employee.csv";

    @Override
    protected InputStreamReader loadCsvFileFromClasspath() {
        return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DEFAULT_EMPLOYEE_CSV_FILE));
    }

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
