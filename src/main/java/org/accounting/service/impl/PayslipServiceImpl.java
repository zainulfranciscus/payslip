package org.accounting.service.impl;

import org.accounting.model.employee.Employee;
import org.accounting.model.payslip.Payslip;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;
import org.accounting.service.PayslipService;
import org.accounting.repository.EmployeeRepository;
import org.accounting.repository.PayslipRepository;

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
    public void writePayslips(SpecificationForReadingEmployeeData specificationForReadingEmployeeData) throws Exception {

        List<Employee> employees = new ArrayList<>();

        payslipRepository.writeHeader();

        while((employees = employeeRepository.find(specificationForReadingEmployeeData)).size() > 0) {
            List<Payslip> payslips = createPayslips(employees);
            payslipRepository.savePayslips(payslips);
            specificationForReadingEmployeeData.reset();
        }

    }

    @Override
    public void setPayslipRepository(PayslipRepository payslipRepository) {
        this.payslipRepository = payslipRepository;
    }

    @Override
    public List<Payslip> createPayslips(List<Employee> employees) throws Exception {
        return payslipRepository.createPayslips(employees);
    }

    @Override
    public void close() throws Exception {
        employeeRepository.close();
        payslipRepository.close();
    }
}


