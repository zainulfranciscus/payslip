package org.myob.service.builder;

import org.myob.persistence.reader.FileReaderType;
import org.myob.persistence.reader.Reader;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.persistence.writer.PayslipWriterImpl;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.EmployeeRepositoryImpl;
import org.myob.repository.impl.PayslipRepositoryImpl;
import org.myob.repository.impl.TaxRepositoryImpl;
import org.myob.repository.EmployeeRepository;
import org.myob.repository.PayslipRepository;
import org.myob.service.PayslipService;
import org.myob.service.impl.PayslipServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public abstract class AbstractPayslipServiceBuilder {

    protected static FileReaderType readerType;
    protected String employeeFileName;
    protected String taxFileName;
    protected String payslipFileName;
    protected Reader taxReader;
    protected Reader employeeReader;
    private Writer writer;

    public AbstractPayslipServiceBuilder withEmployeeFileName(String employeeFileName) {
        this.employeeFileName = employeeFileName;
        return this;
    }

    public AbstractPayslipServiceBuilder withTaxFileName(String taxFileName) {
        this.taxFileName = taxFileName;
        return this;
    }

    public AbstractPayslipServiceBuilder withPayslipFileName(String payslipFileName) throws IOException {
        this.payslipFileName = payslipFileName;
        return this;
    }

    public AbstractPayslipServiceBuilder withFileReaderType(FileReaderType readerType) {
        this.readerType = readerType;
        return this;
    }

    public AbstractPayslipServiceBuilder withReaderForTaxRepository(Reader reader) {
        this.taxReader = reader;
        return this;
    }

    public AbstractPayslipServiceBuilder withReaderForEmployeeRepository(Reader reader) {
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


        if (this.payslipFileName != null) {
            PayslipWriter payslipWriter = new PayslipWriterImpl();
            payslipWriter.setWriter(new FileWriter(this.payslipFileName));

            payslipRepository.setWriter(payslipWriter);
        }

        return payslipRepository;
    }


    public PayslipService build() throws Exception {

        PayslipService payslipService = new PayslipServiceImpl();
        payslipService.setEmployeeRepository(createEmployeeRepository());
        payslipService.setPayslipRepository(createPayslipRepository());

        return payslipService;
    }
}
