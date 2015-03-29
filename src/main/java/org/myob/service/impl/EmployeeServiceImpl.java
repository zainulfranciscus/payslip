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
    public void createPayslips(String employeeFileName,String taxFileName, String payslipFileName) throws Exception {

        /*PayslipServiceBuilder builder = new PayslipServiceBuilderImpl();
        builder.fileReaderType(FileReaderType.CLASSLOADER);
        builder.withEmployeeFileName(employeeFileName);
        builder.withTaxFileName(taxFileName);
        builder.withPayslipFileName(payslipFileName);*/

        payslipService.writePayslips(new EmployeeSpecificationBuilder().build());
        payslipService.close();
    }

    @Override
    public void setPayslipService(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

}
