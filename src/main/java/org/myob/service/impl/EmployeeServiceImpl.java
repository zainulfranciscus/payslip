package org.myob.service.impl;

import org.myob.repository.specification.EmployeeSpecification;
import org.myob.service.EmployeeService;
import org.myob.service.PayslipService;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private PayslipService payslipService;

    @Override
    public void writePayslips() throws Exception {
        payslipService.writePayslips(new EmployeeSpecification());
        payslipService.close();
    }

    @Override
    public void setPayslipService(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

}
