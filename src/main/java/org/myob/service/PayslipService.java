package org.myob.service;

import org.myob.model.employee.Employee;
import org.myob.model.payslip.Payslip;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;

import java.util.List;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipService {

    void setEmployeeRepository(EmployeeRepository employeeRepository);

    void writePayslips(EmployeeSpecification employeeSpecification) throws Exception;

    void setPayslipRepository(PayslipRepository payslipRepository);

    List<Payslip> createPayslips(List<Employee> employees) throws Exception;

    void close() throws Exception;
}
