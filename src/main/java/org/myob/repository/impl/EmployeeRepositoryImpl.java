package org.myob.repository.impl;

import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.persistence.mapping.impl.EmployeeHeader;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;
import org.myob.persistence.row.Row;
import org.myob.persistence.reader.Reader;
import org.myob.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.myob.persistence.mapping.impl.EmployeeHeader.*;

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
    public List<Employee> find(EmployeeSpecification specification) throws Exception {
        Row row = null;

        List<Employee> employees = new ArrayList<Employee>();

        while((row = reader.read(new EmployeeRowSpecification())) != null && !specification.hasReadTheAllowedNumberOfLines()) {

            Employee employee = new EmployeeBuilder()
                    .withStartPaymentPeriod(row.getInt(START_PAYMENT_YEAR),
                            row.getMonthAsInt(START_PAYMENT_MONTH),
                            row.getInt(START_PAYMENT_DATE))
                    .withEndPaymentPeriod(row.getInt(END_PAYMENT_YEAR),
                            row.getMonthAsInt(END_PAYMENT_MONTH),
                            row.getInt(END_PAYMENT_DATE))
                    .withFirstName(row.get(FIRST_NAME))
                    .withLastName(row.get(LAST_NAME))
                    .withSalary(row.getInt(ANNUAL_SALARY))
                    .withSuperRate(row.get(SUPER_RATE))
                    .build();

            employees.add(employee);


            specification.incrementNumberOfLineRead();
        }

        return employees;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
