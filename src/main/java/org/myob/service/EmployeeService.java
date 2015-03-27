package org.myob.service;

import org.joda.time.LocalDate;
import org.myob.employee.Employee;
import org.myob.employee.EmployeePayslip;
import org.myob.repository.TaxRepository;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeeService {

    EmployeePayslip payslip(LocalDate startDate, LocalDate endDate, Employee employee) throws IOException;
    void setTaxRepository(TaxRepository taxRepository);
}
