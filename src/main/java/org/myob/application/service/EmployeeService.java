package org.myob.application.service;

import org.myob.domain.service.PayslipService;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public interface EmployeeService {
    void createPayslips(String employeeFileName, String taxFileName, String payslipFileName) throws Exception;

}
