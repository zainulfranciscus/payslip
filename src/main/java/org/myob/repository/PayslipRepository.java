package org.myob.repository;

import org.myob.model.employee.Employee;
import org.myob.model.payslip.Payslip;
import org.myob.persistence.writer.PayslipWriter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface PayslipRepository {

    Payslip createPayslipForThisEmployee(Employee employee) throws Exception;

    void save(Payslip payslip) throws IOException;

    List<Payslip> createPayslips(List<Employee> employees) throws Exception;

    void savePayslips(List<Payslip> payslips) throws IOException;

    void setTaxRepository(TaxRepository taxRepository);

    void setWriter(PayslipWriter payslipWriter);

    void writeHeader() throws IOException;

    void close() throws Exception;
}
