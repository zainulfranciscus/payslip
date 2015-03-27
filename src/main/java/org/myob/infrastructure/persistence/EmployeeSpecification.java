package org.myob.infrastructure.persistence;

import org.myob.domain.model.employee.Employee;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface EmployeeSpecification extends Specification<Employee>{
    int numberOfLineRead();
    void incrementNumberOfLineRead();
    boolean hasReadTheAllowedNumberOfLines();
}
