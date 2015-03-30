package org.myob.service.impl;

import org.myob.model.employee.Employee;
import org.myob.model.payslip.Payslip;
import org.myob.repository.specification.SpecificationForReadingEmployeeData;
import org.myob.service.PayslipService;
import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;

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


