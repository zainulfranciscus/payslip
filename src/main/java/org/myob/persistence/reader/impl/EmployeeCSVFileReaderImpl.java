package org.myob.persistence.reader.impl;

import org.apache.commons.csv.CSVRecord;
import org.myob.persistence.mapping.RowHeader;
import org.myob.persistence.mapping.impl.EmployeeHeader;
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
    protected String csvFileFromClasspath() {
        return DEFAULT_EMPLOYEE_CSV_FILE;
    }

    @Override
    public Row make(CSVRecord record) {
        return new EmployeeCsvRowBuilder()
                .withFirstName(record.get(FIRST_NAME.toString()))
                .withLastName(record.get(LAST_NAME.toString()))
                .withSalary(record.get(ANNUAL_SALARY.toString()))
                .withSuper(record.get(SUPER_RATE.toString()))
                .withStartOfPaymentDate(record.get(START_PAYMENT_DATE.toString()))
                .withStartOfPaymentMonth(record.get(START_PAYMENT_MONTH.toString()))
                .withStartOfPaymentYear(record.get(START_PAYMENT_YEAR.toString()))
                .withEndOfPaymentDate(record.get(END_PAYMENT_DATE.toString()))
                .withEndOfPaymentMonth(record.get(END_PAYMENT_MONTH.toString()))
                .withEndOfPaymentYear(record.get(END_PAYMENT_YEAR.toString()))
                .build();

    }

    @Override
    protected String[] headers() {
        EmployeeHeader[] employeeHeaders = EmployeeHeader.values();
        String [] headers = new String [employeeHeaders.length];

        for(int i= 0; i < employeeHeaders.length; i++){
            headers[i] = employeeHeaders[i].toString();
        }
        return headers;
    }
}
