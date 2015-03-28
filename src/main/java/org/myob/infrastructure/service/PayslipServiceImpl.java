package org.myob.infrastructure.service;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.service.EmployeeSpecification;
import org.myob.domain.service.PayslipService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class PayslipServiceImpl implements PayslipService {

    private EmployeeRepository employeeRepository;
    private PayslipRepository payslipRepository;

    @Override
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void writePayslips(EmployeeSpecification employeeSpecification) throws Exception {

        List<Employee> employees = new ArrayList<>();

        while((employees = employeeRepository.find(employeeSpecification)).size() > 0) {
            List<Payslip> payslips = createPayslips(employees);
            payslipRepository.savePayslips(payslips);
        }
    }

    @Override
    public void setPayslipRepository(PayslipRepository payslipRepository) {
        this.payslipRepository = payslipRepository;
    }

    @Override
    public List<Payslip> createPayslips(List<Employee> employees) throws Exception {
        List<Payslip> payslips = payslipRepository.createPayslips(employees);
        return payslips;
    }
}


