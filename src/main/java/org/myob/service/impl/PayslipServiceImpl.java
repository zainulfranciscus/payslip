package org.myob.service.impl;

import org.myob.domain.model.employee.EmployeeRepository;
import org.myob.service.EmployeeSpecification;
import org.myob.service.PayslipService;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class PayslipServiceImpl implements PayslipService {

    private EmployeeRepository employeeRepository;

    @Override
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void writePayslips(EmployeeSpecification employeeSpecification) throws IOException {
        employeeRepository.find(employeeSpecification);
    }
}


