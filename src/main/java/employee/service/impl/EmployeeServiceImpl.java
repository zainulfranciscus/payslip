package employee.service.impl;

import builder.TaxCriteriaBuilder;
import payslip.MONTH;
import domain.Tax;
import employee.Employee;
import employee.EmployeePayslip;
import employee.EmployeePayslipFactory;

import employee.service.EmployeeService;
import payslip.EmployeePayslipFactoryImpl;
import repository.TaxRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private TaxRepository taxRepository;

    @Override
    public EmployeePayslip payslip(MONTH month, Employee employee) throws IOException {

        Tax tax = taxRepository.find(new TaxCriteriaBuilder().withEmployee(employee).build());

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        return payslipFactory.createWith(MONTH.APRIL,employee,tax);

    }

    @Override
    public void setTaxRepository(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }
}
