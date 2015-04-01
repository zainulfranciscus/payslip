package org.accounting.service;

import org.accounting.model.employee.Employee;
import org.accounting.model.payslip.Payslip;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;
import org.accounting.repository.EmployeeRepository;
import org.accounting.repository.PayslipRepository;

import java.util.List;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface PayslipService {

    void setEmployeeRepository(EmployeeRepository employeeRepository);

    void writePayslips(SpecificationForReadingEmployeeData specificationForReadingEmployeeData) throws Exception;

    void setPayslipRepository(PayslipRepository payslipRepository);

    List<Payslip> createPayslips(List<Employee> employees) throws Exception;

    void close() throws Exception;
}
