package org.myob.infrastructure.service;

import org.junit.Test;
import org.myob.domain.service.PayslipService;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.myob.infrastructure.persistence.file.reader.FileReaderType.CLASSLOADER;


/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderTest {

    private PayslipServiceBuilderImpl payslipServiceBuilder = new PayslipServiceBuilderImpl();

    @Test
    public void shouldNotReturnNull_withACLassLoaderReader() throws Exception {
        PayslipService payslipService = payslipServiceBuilder.withEmployeeFileName("employee/employee.csv")
                .withTaxFileName("tax/tax.csv")
                .withPayslipFileName("payslip.csv")
                .fileReaderType(CLASSLOADER)
                .build();

        assertNotNull(payslipService);
    }
}
