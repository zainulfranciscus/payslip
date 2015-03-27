package org.myob.infrastructure.persistence.file.reader.impl;

import org.myob.infrastructure.persistence.file.reader.AbstractCsvReader;
import org.myob.infrastructure.persistence.file.reader.EmployeeHeader;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.persistence.file.reader.builder.EmployeeCsvRowBuilder;
import org.apache.commons.csv.CSVRecord;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReaderImpl extends AbstractCsvReader {
    public EmployeeCSVFileReaderImpl(String fileName) {
        super(fileName);
    }

    @Override
    public Row make(CSVRecord record) {
        return new EmployeeCsvRowBuilder().withFirstName(record.get(EmployeeHeader.FIRST_NAME.getLabel()))
                .withLastName(record.get(EmployeeHeader.LAST_NAME.getLabel()))
                .withPaymentDate(record.get(EmployeeHeader.PAYMENT_DATE.getLabel()))
                .withSalary(record.get(EmployeeHeader.ANNUAL_SALARY.getLabel()))
                .withSuper(record.get(EmployeeHeader.SUPER_RATE.getLabel()))
                .build();
    }
}
