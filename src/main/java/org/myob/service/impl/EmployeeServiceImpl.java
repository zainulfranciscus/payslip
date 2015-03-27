package org.myob.service.impl;

import org.joda.time.LocalDate;
import org.myob.employee.Employee;
import org.myob.employee.EmployeePayslip;
import org.myob.employee.EmployeePayslipFactory;
import org.myob.payslip.EmployeePayslipFactoryImpl;
import org.myob.repository.TaxRepository;
import org.myob.service.EmployeeService;
import org.myob.tax.Tax;
import org.myob.tax.TaxSpecificationBuilder;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private TaxRepository taxRepository;

    @Override
    public EmployeePayslip payslip(LocalDate startDate, LocalDate endDate, Employee employee) throws IOException {

        Tax tax = taxRepository.find(new TaxSpecificationBuilder().withEmployee(employee).build());

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        return payslipFactory.createWith(startDate,endDate,employee,tax);

    }

    @Override
    public void setTaxRepository(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

}
