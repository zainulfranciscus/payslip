package org.myob.repository.specification;

import org.myob.model.employee.Employee;
import org.myob.repository.specification.Specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface EmployeeSpecification  {
    int numberOfEmployeesLoadedToMemory();
    void incrementNumberOfLineRead();
    boolean hasReadTheAllowedNumberOfLines();
}
