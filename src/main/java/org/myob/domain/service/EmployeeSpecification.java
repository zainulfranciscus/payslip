package org.myob.domain.service;

import org.myob.domain.model.employee.Employee;
import org.myob.infrastructure.repository.Specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface EmployeeSpecification extends Specification<Employee> {
    int numberOfEmployeesLoadedToMemory();
    void incrementNumberOfLineRead();
    boolean hasReadTheAllowedNumberOfLines();
}
