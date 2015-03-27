package org.myob.infrastructure.service;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.service.EmployeeSpecification;
import org.myob.infrastructure.repository.Reader;

import java.io.IOException;
import java.util.List;


/**
 * Created by Zainul Franciscsu on 27/03/2015.
 */
public interface EmployeeRepository {
    void setReader(Reader reader);
    List<Employee> find(EmployeeSpecification specification) throws IOException;
}
