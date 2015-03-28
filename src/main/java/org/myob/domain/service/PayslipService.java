package org.myob.domain.service;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.infrastructure.service.EmployeeRepository;
import org.myob.infrastructure.service.PayslipRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipService {

    void setEmployeeRepository(EmployeeRepository employeeRepository);

    void writePayslips(EmployeeSpecification employeeSpecification) throws Exception;

    void setPayslipRepository(PayslipRepository payslipRepository);

    List<Payslip> createPayslips(List<Employee> employees) throws Exception;
}
