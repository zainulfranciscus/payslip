package org.myob.application.service;

import org.myob.domain.service.PayslipService;
import org.myob.infrastructure.persistence.file.reader.FileReaderType;
import org.myob.infrastructure.repository.Reader;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.impl.EmployeeRepositoryImpl;
import org.myob.infrastructure.repository.impl.PayslipRepositoryImpl;
import org.myob.infrastructure.repository.impl.TaxRepositoryImpl;
import org.myob.infrastructure.service.EmployeeRepository;
import org.myob.infrastructure.service.PayslipRepository;
import org.myob.infrastructure.service.PayslipServiceImpl;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public abstract class PayslipServiceBuilder {

    protected static FileReaderType readerType;
    protected String employeeFileName;
    protected String taxFileName;
    protected String payslipFileName;
    protected Reader taxReader;
    protected Reader employeeReader;

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

    public PayslipServiceBuilder fileReaderType(FileReaderType readerType) {
        this.readerType = readerType;
        return this;
    }

    public PayslipServiceBuilder setReaderForTaxRepository(Reader reader) {
        this.taxReader = reader;
        return this;
    }

    public PayslipServiceBuilder setReaderForEmployeeRepository(Reader reader) {
        this.employeeReader = reader;
        return this;
    }


    public TaxRepository createTaxRepository() throws Exception {

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(this.taxReader);
        return taxRepository;
    }


    public EmployeeRepository createEmployeeRepository() throws Exception {

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(this.employeeReader);
        return employeeRepository;
    }

    public PayslipRepository createPayslipRepository() throws Exception {
        PayslipRepository payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(createTaxRepository());
        return payslipRepository;
    }

    public PayslipService build() throws Exception {

        PayslipService payslipService = new PayslipServiceImpl();
        payslipService.setEmployeeRepository(createEmployeeRepository());
        payslipService.setPayslipRepository(createPayslipRepository());

        return payslipService;
    }
}
