package org.myob.infrastructure.service;

import org.myob.domain.service.PayslipService;
import org.myob.infrastructure.persistence.file.reader.FileReaderType;
import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.infrastructure.persistence.file.reader.impl.TaxCSVReaderImpl;
import org.myob.infrastructure.persistence.file.writer.PayslipWriterImpl;
import org.myob.infrastructure.repository.PayslipWriter;
import org.myob.infrastructure.repository.Reader;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.impl.EmployeeRepositoryImpl;
import org.myob.infrastructure.repository.impl.PayslipRepositoryImpl;
import org.myob.infrastructure.repository.impl.TaxRepositoryImpl;

import java.io.*;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public class PayslipServiceBuilder {

    private static FileReaderType readerType;
    private String employeeFileName;
    private String taxFileName;

    private Writer writer;

    public PayslipServiceBuilder withEmployeeFileName(String employeeFileName) {
        this.employeeFileName = employeeFileName;
        return this;
    }

    public PayslipServiceBuilder withTaxFileName(String taxFileName) {
        this.taxFileName = taxFileName;
        return this;
    }

    public PayslipService build() throws FileNotFoundException {

        Reader taxCsvFileReader = new TaxCSVReaderImpl();
        taxCsvFileReader.setDataSourceReader(readerType.getReader(taxFileName));

        Reader employeeCsvFileReader = new EmployeeCSVFileReaderImpl();
        employeeCsvFileReader.setDataSourceReader(readerType.getReader(employeeFileName));

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(taxCsvFileReader);

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(employeeCsvFileReader);

        PayslipWriter payslipWriter = new PayslipWriterImpl();
        payslipWriter.setWriter(writer);

        PayslipRepository payslipRepository = new PayslipRepositoryImpl();
        payslipRepository.setTaxRepository(taxRepository);
        payslipRepository.setWriter(payslipWriter);

        PayslipService payslipService = new PayslipServiceImpl();
        payslipService.setEmployeeRepository(employeeRepository);
        payslipService.setPayslipRepository(payslipRepository);

        return payslipService;
    }

    public PayslipServiceBuilder withFileWriter(String payslipFileName) throws IOException {
        this.writer = new FileWriter(payslipFileName,true);
        return this;
    }

    public PayslipServiceBuilder withStringWriter() {
        this.writer = new StringWriter();
        return this;
    }

    public PayslipServiceBuilder fileReaderType(FileReaderType readerType) {
        this.readerType = readerType;
        return this;
    }
}
