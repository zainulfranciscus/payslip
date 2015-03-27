package org.myob.domain.service;

import org.myob.infrastructure.service.EmployeeRepository;
import org.myob.infrastructure.service.PayslipRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipService {

    void setEmployeeRepository(EmployeeRepository mockEmployeeRepository);

    void writePayslips(EmployeeSpecification employeeSpecification) throws IOException;

    void setPayslipRepository(PayslipRepository payslipRepository);
}
