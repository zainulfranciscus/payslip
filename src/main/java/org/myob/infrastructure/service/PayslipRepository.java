package org.myob.infrastructure.service;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.PayslipWriter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface PayslipRepository {

    Payslip create(Employee employee) throws Exception;

    void setTaxRepository(TaxRepository taxRepository);

    void setWriter(PayslipWriter payslipWriter);

    void save(Payslip payslip) throws IOException;

    List<Payslip> createPayslips(List<Employee> employees) throws Exception;

    void savePayslips(List<Payslip> payslips) throws IOException;
}
