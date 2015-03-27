package org.myob.service;

import org.myob.domain.model.employee.EmployeeRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipService {

    void setEmployeeRepository(EmployeeRepository mockEmployeeRepository);

    void writePayslips(EmployeeSpecification employeeSpecification) throws IOException;
}
