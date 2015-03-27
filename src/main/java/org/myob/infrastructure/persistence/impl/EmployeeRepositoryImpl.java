package org.myob.infrastructure.persistence.impl;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.infrastructure.persistence.EmployeeSpecification;
import org.myob.infrastructure.persistence.Reader;
import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.domain.model.employee.EmployeeRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.myob.infrastructure.persistence.file.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Reader reader;

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<Employee> find(EmployeeSpecification specification) throws IOException {
        Row row = null;

        List<Employee> employees = new ArrayList<Employee>();

        while((row = reader.read()) != null && !specification.hasReadTheAllowedNumberOfLines()) {
            Employee employee = new EmployeeBuilder()
                    .withEndOfPaymentDate(row.getInt(END_PAYMENT_DATE))
                    .withEndOfPaymentMonth(row.getInt(END_PAYMENT_MONTH))
                    .withEndOfPaymentYear(row.getInt(END_PAYMENT_YEAR))
                    .withStartOfPaymentDate(row.getInt(START_PAYMENT_DATE))
                    .withStartOfPaymentMonth(row.getInt(START_PAYMENT_MONTH))
                    .withStartOfPaymentYear(row.getInt(START_PAYMENT_YEAR))
                    .withFirstName(row.get(FIRST_NAME))
                    .withLastName(row.get(LAST_NAME))
                    .withSalary(row.getInt(ANNUAL_SALARY))
                    .withSuperRate(row.get(SUPER_RATE))
                    .build();
            if (specification.match(employee)) {
                employees.add(employee);
            }

            specification.incrementNumberOfLineRead();
        }

        reader.close();

        return employees;
    }
}
