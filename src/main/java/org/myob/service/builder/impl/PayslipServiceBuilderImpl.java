package org.myob.service.builder.impl;

import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.reader.impl.TaxCSVReaderImpl;
import org.myob.persistence.writer.PayslipWriterImpl;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.repository.TaxRepository;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilderImpl extends AbstractPayslipServiceBuilder {

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
        this.taxReader.setDataSourceReader(new FileReader(taxFileName));
        return super.createTaxRepository();
    }

    @Override
    public EmployeeRepository createEmployeeRepository() throws Exception {

        this.employeeReader.setDataSourceReader(new FileReader(employeeFileName));
        return super.createEmployeeRepository();
    }


}
