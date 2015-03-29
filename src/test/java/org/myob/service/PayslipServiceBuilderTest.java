package org.myob.service;

import org.junit.Test;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.reader.impl.TaxCSVReaderImpl;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.service.builder.impl.PayslipServiceBuilderImpl;

import static junit.framework.TestCase.assertNotNull;
import static org.myob.persistence.reader.FileReaderType.CLASSLOADER;


/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderTest {

    private AbstractPayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilderImpl();

    @Test
    public void shouldNotReturnNull_withACLassLoaderReader() throws Exception {
        PayslipService payslipService = payslipServiceBuilder.withEmployeeFileName("employee/employee.csv")
                .withTaxFileName("tax/tax.csv")
                .withPayslipFileName("payslip.csv")
                .withFileReaderType(CLASSLOADER)
                .withReaderForTaxRepository(new TaxCSVReaderImpl())
                .withReaderForEmployeeRepository(new EmployeeCSVFileReaderImpl())
                .build();

        assertNotNull(payslipService);
    }
}
