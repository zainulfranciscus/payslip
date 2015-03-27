package org.myob.repository;

import org.myob.employee.Employee;
import org.myob.employee.EmployeeSpecification;

import java.io.IOException;
import java.util.List;


/**
 * Created by Zainul Franciscsu on 27/03/2015.
 */
public interface EmployeeRepository {
    void setReader(Reader reader);

    List<Employee> find(EmployeeSpecification specification) throws IOException;
}
