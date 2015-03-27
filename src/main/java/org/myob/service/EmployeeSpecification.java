package org.myob.service;

import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.persistence.Specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface EmployeeSpecification extends Specification<Employee> {
    int numberOfLineRead();
    void incrementNumberOfLineRead();
    boolean hasReadTheAllowedNumberOfLines();
}
