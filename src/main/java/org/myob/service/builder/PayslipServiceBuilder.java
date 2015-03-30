package org.myob.service.builder;

import org.apache.commons.lang3.StringUtils;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.persistence.reader.impl.TaxCSVReaderImpl;
import org.myob.persistence.row.specification.impl.EmployeeRowSpecification;
import org.myob.persistence.row.specification.impl.TaxRowSpecification;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.persistence.writer.impl.PayslipWriterImpl;
import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.EmployeeRepositoryImpl;
import org.myob.repository.impl.PayslipRepositoryImpl;
import org.myob.repository.impl.TaxRepositoryImpl;
import org.myob.service.PayslipService;
import org.myob.service.impl.PayslipServiceImpl;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipServiceBuilder {

    protected String employeeFileName;
    protected String taxFileName;
    protected String payslipFileName;
    protected Reader taxReader;
    protected Reader employeeReader;

    public PayslipServiceBuilder() {
        this.taxReader = new TaxCSVReaderImpl();
        this.employeeReader = new EmployeeCSVFileReaderImpl();
    }

    public PayslipServiceBuilder withEmployeeFileName(String employeeFileName) {
        this.employeeFileName = employeeFileName;
        return this;
    }

    public PayslipServiceBuilder withTaxFileName(String taxFileName) {
        this.taxFileName = taxFileName;
        return this;
    }

    public PayslipServiceBuilder withPayslipFileName(String payslipFileName) throws IOException {
        this.payslipFileName = payslipFileName;
        return this;
    }


    public PayslipServiceBuilder withReaderForTaxRepository(Reader reader) {
        this.taxReader = reader;
        return this;
    }

    public PayslipServiceBuilder withReaderForEmployeeRepository(Reader reader) {
        this.employeeReader = reader;
        return this;
    }

    public EmployeeRepository createEmployeeRepository() throws Exception {

        this.employeeReader.setFileName(employeeFileName);
        this.employeeReader.initializeFileReader();
        this.employeeReader.setSpecification(new EmployeeRowSpecification());

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(this.employeeReader);
        return employeeRepository;
    }

    public PayslipService build() throws Exception {

        PayslipService payslipService = new PayslipServiceImpl();
        payslipService.setEmployeeRepository(createEmployeeRepository());
        payslipService.setPayslipRepository(createPayslipRepository());

        return payslipService;
    }

    private TaxRepository createTaxRepository() throws Exception {

        this.taxReader.setFileName(taxFileName);
        this.taxReader.initializeFileReader();
        this.taxReader.setSpecification(new TaxRowSpecification());

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(this.taxReader);
        return taxRepository;
    }

    private PayslipRepository createPayslipRepository() throws Exception {

        PayslipRepository payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(createTaxRepository());


        if (StringUtils.isNotBlank(payslipFileName)) {
            PayslipWriter payslipWriter = new PayslipWriterImpl();
            payslipWriter.setWriter(new FileWriter(this.payslipFileName));
            payslipRepository.setWriter(payslipWriter);
        }

        return payslipRepository;
    }
}
