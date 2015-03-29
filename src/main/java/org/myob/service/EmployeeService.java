package org.myob.service;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public interface EmployeeService {
    void writePayslips() throws Exception;
    void setPayslipService(PayslipService payslipService);

}
