package org.myob.repository.impl;

import org.myob.model.employee.Employee;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.model.tax.Tax;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.repository.PayslipRepository;
import org.myob.repository.TaxRepository;
import org.myob.repository.specification.TaxSpecification;
import org.myob.repository.PayslipCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipRepositoryImpl implements PayslipRepository {

    private TaxRepository taxRepository;
    private PayslipWriter payslipWriter;

    @Override
    public List<Payslip> createPayslips(List<Employee> employees) throws Exception {

        List<Payslip> payslips = new ArrayList<>();
        for (Employee employee : employees) {
            payslips.add(create(employee));
        }

        return payslips;
    }


    @Override
    public Payslip create(Employee employee) throws Exception {

        Tax tax = taxRepository.find(new TaxSpecification(employee));

        PayslipCalculator calc =  new PayslipCalculator(employee,tax);
        return new PayslipBuilder()
                .withGrossIncome(calc.getGrossIncome())
                .withIncomeTax(calc.getIncomeTax())
                .withNetIncome(calc.getNetIncome())
                .withName(calc.getEmployeeName())
                .withPayPeriod(calc.getPayPeriod())
                .withSuper(calc.getSuper()).build();

    }


    @Override
    public void savePayslips(List<Payslip> payslips) throws IOException {
        for (Payslip payslip : payslips) {
            save(payslip);
        }
    }

    @Override
    public void writeHeader() throws IOException {
        if(payslipWriter != null) {
            payslipWriter.writeHeader();
        }
    }

    @Override
    public void save(Payslip payslip) throws IOException {
        if(payslipWriter != null) {
            payslipWriter.write(payslip);
        }

    }

    @Override
    public void setTaxRepository(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    @Override
    public void setWriter(PayslipWriter payslipWriter) {
        this.payslipWriter = payslipWriter;
    }

    @Override
    public void close() throws Exception {
        if(taxRepository != null) {
            taxRepository.close();
        }

        if(payslipWriter != null) {
            payslipWriter.close();
        }

    }
}
