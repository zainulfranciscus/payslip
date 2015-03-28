package org.myob.infrastructure.service;

import org.myob.application.service.PayslipServiceBuilder;
import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;
import org.myob.infrastructure.persistence.file.writer.PayslipWriterImpl;
import org.myob.infrastructure.repository.PayslipWriter;
import org.myob.infrastructure.repository.TaxRepository;

import java.io.FileWriter;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderImpl extends PayslipServiceBuilder {

    public PayslipServiceBuilderImpl(){
        this.taxReader = new TaxCSVReaderImpl();
        this.employeeReader = new EmployeeCSVFileReaderImpl();
    }
    @Override
    public PayslipRepository createPayslipRepository() throws Exception {
        PayslipRepository payslipRepository = super.createPayslipRepository();

        PayslipWriter payslipWriter = new PayslipWriterImpl();
        payslipWriter.setWriter(new FileWriter(this.payslipFileName));

        payslipRepository.setWriter(payslipWriter);
        return payslipRepository;
    }

    @Override
    public TaxRepository createTaxRepository() throws Exception {
        this.taxReader.setDataSourceReader(readerType.getReader(taxFileName));
        return super.createTaxRepository();
    }

    @Override
    public EmployeeRepository createEmployeeRepository() throws Exception {

        this.employeeReader.setDataSourceReader(readerType.getReader(employeeFileName));
        return super.createEmployeeRepository();
    }


}
