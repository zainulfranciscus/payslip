package org.myob.repository;

import org.myob.model.employee.Employee;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.persistence.reader.Reader;

import java.util.List;


/**
 * Created by Zainul Franciscsu on 27/03/2015.
 */
public interface EmployeeRepository {
    void setReader(Reader reader);

    List<Employee> find(EmployeeSpecification specification) throws Exception;

    void close() throws Exception;
}
