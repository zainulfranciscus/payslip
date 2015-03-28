package org.myob.application.service.impl;

import org.myob.application.service.EmployeeService;
import org.myob.application.service.PayslipServiceBuilder;
import org.myob.domain.model.employee.EmployeeSpecificationBuilder;
import org.myob.infrastructure.persistence.file.reader.FileReaderType;
import org.myob.infrastructure.service.PayslipServiceBuilderImpl;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void createPayslips(String employeeFileName,String taxFileName, String payslipFileName) throws Exception {

        PayslipServiceBuilder builder = new PayslipServiceBuilderImpl();
        builder.fileReaderType(FileReaderType.CLASSLOADER);
        builder.withEmployeeFileName("employee/employee.csv");
        builder.withTaxFileName("tax/tax.csv");
        builder.withPayslipFileName(payslipFileName);

        builder.build().writePayslips(new EmployeeSpecificationBuilder().build());
    }

}
