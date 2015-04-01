package org.accounting.repository;

import org.accounting.model.employee.Employee;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;
import org.accounting.persistence.reader.Reader;

import java.util.List;


/**
 * Created by Zainul Franciscsu on 27/03/2015.
 */
public interface EmployeeRepository {
    void setReader(Reader reader);

    List<Employee> find(SpecificationForReadingEmployeeData specification) throws Exception;

    void close() throws Exception;
}
