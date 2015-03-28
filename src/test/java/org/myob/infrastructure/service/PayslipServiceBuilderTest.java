package org.myob.infrastructure.service;

import org.junit.Test;
import org.myob.domain.service.PayslipService;
import org.myob.infrastructure.persistence.file.reader.FileReaderType;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.myob.infrastructure.persistence.file.reader.FileReaderType.CLASSLOADER;


/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderTest {

    private PayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilder();

    @Test
    public void shouldNotReturnNull_withACLassLoaderReader() throws IOException {
        PayslipService payslipService = payslipServiceBuilder.withEmployeeFileName("employee/employee.csv")
                .withTaxFileName("tax/tax.csv")
                .withStringWriter()
                .fileReaderType(CLASSLOADER)
                .build();

        assertNotNull(payslipService);
    }
}
