package org.myob.repository.impl;

import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.myob.employee.EmployeeSpecification;
import org.myob.repository.Reader;
import org.myob.reader.Row;
import org.myob.repository.EmployeeRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.myob.reader.EmployeeHeader.*;

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

        while((row = reader.read()) != null) {
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
        }

        reader.close();

        return employees;
    }
}
