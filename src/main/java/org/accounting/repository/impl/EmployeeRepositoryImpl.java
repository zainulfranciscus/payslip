package org.accounting.repository.impl;

import org.accounting.model.employee.Employee;
import org.accounting.model.employee.EmployeeBuilder;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;
import org.accounting.persistence.row.Row;
import org.accounting.persistence.reader.Reader;
import org.accounting.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.accounting.persistence.mapping.impl.EmployeeHeader.*;

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
    public List<Employee> find(SpecificationForReadingEmployeeData specification) throws Exception {
        Row row = null;

        List<Employee> employees = new ArrayList<Employee>();

        while(!specification.hasLoadTheAllowedNumberOfEmployeesToMemory() &&(row = reader.read()) != null) {

            Employee employee = new EmployeeBuilder()
                    .withStartPaymentPeriod(
                            row.getInt(START_PAYMENT_YEAR),
                            row.getMonthAsInt(START_PAYMENT_MONTH),
                            row.getInt(START_PAYMENT_DATE))
                    .withEndPaymentPeriod(
                            row.getInt(END_PAYMENT_YEAR),
                            row.getMonthAsInt(END_PAYMENT_MONTH),
                            row.getInt(END_PAYMENT_DATE))
                    .withFirstName(row.get(FIRST_NAME))
                    .withLastName(row.get(LAST_NAME))
                    .withSalary(row.getDouble(ANNUAL_SALARY))
                    .withSuperRate(row.get(SUPER_RATE))
                    .build();

            employees.add(employee);
            specification.incrementNumberOfEmployeeLoadedToMemory();
        }

        return employees;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
