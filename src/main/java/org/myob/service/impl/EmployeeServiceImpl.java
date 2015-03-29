package org.myob.service.impl;

import org.myob.service.EmployeeService;
import org.myob.repository.specification.EmployeeSpecificationBuilder;
import org.myob.service.PayslipService;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private PayslipService payslipService;

    @Override
    public void writePayslips() throws Exception {
        payslipService.writePayslips(new EmployeeSpecificationBuilder().build());
        payslipService.close();
    }

    @Override
    public void setPayslipService(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

}
