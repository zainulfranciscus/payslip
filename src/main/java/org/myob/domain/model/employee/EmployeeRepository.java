package org.myob.domain.model.employee;

import org.myob.infrastructure.persistence.EmployeeSpecification;
import org.myob.infrastructure.persistence.Reader;

import java.io.IOException;
import java.util.List;


/**
 * Created by Zainul Franciscsu on 27/03/2015.
 */
public interface EmployeeRepository {
    void setReader(Reader reader);

    List<Employee> find(EmployeeSpecification specification) throws IOException;
}
