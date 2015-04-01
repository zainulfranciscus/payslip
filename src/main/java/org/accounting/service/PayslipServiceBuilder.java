package org.accounting.service;

import org.apache.commons.lang3.StringUtils;
import org.accounting.persistence.reader.Reader;
import org.accounting.persistence.reader.impl.EmployeeCSVFileReaderImpl;
import org.accounting.persistence.reader.impl.TaxCSVReaderImpl;
import org.accounting.persistence.row.specification.impl.EmployeeRowSpecification;
import org.accounting.persistence.row.specification.impl.TaxRowSpecification;
import org.accounting.persistence.writer.PayslipWriter;
import org.accounting.persistence.writer.impl.PayslipWriterImpl;
import org.accounting.repository.EmployeeRepository;
import org.accounting.repository.PayslipRepository;
import org.accounting.repository.TaxRepository;
import org.accounting.repository.impl.EmployeeRepositoryImpl;
import org.accounting.repository.impl.PayslipRepositoryImpl;
import org.accounting.repository.impl.TaxRepositoryImpl;
import org.accounting.service.impl.PayslipServiceImpl;

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
