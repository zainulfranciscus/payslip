package org.myob.service;

import org.junit.Test;
import org.mockito.Mockito;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.reader.impl.TaxCSVReaderImpl;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.service.builder.impl.PayslipServiceBuilderImpl;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderTest {

    private AbstractPayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilderImpl();

    @Test
    public void shouldReturnAPayslipService() throws Exception {

        TaxCSVReaderImpl mockTaxCsvReader = mock(TaxCSVReaderImpl.class);
        EmployeeCSVFileReaderImpl mockEmployeeCsvReader = mock(EmployeeCSVFileReaderImpl.class);

        PayslipService payslipService = payslipServiceBuilder.withEmployeeFileName(loadFromClassPath("employee/employee.csv"))
                .withTaxFileName(loadFromClassPath("tax/tax.csv"))
                .withPayslipFileName("payslip.csv")
                .withReaderForTaxRepository(mockTaxCsvReader)
                .withReaderForEmployeeRepository(mockEmployeeCsvReader)
                .build();

        assertNotNull(payslipService);
        verify(mockEmployeeCsvReader,times(1)).setDataSourceReader(Mockito.isA(java.io.Reader.class));
        verify(mockEmployeeCsvReader,times(1)).setDataSourceReader(Mockito.isA(java.io.Reader.class));
    }

    private String loadFromClassPath(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
