package reader;

import builder.EmployeeCsvRowBuilder;
import org.apache.commons.csv.CSVRecord;
import reader.impl.CSVReaderImpl;

import static reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCSVFileReader extends CSVReaderImpl {
    public EmployeeCSVFileReader(String fileName) {
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
