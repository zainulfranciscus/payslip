package org.myob.repository.impl;

import org.myob.model.employee.Employee;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.model.tax.Tax;
import org.myob.persistence.writer.PayslipWriter;
import org.myob.repository.PayslipRepository;
import org.myob.repository.TaxRepository;
import org.myob.repository.specification.TaxSpecificationBuilder;

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

        List<Payslip> payslips = new ArrayList<Payslip>();
        for (Employee employee : employees) {
            payslips.add(create(employee));
        }

        return payslips;
    }


    @Override
    public Payslip create(Employee employee) throws Exception {

        Tax tax = taxRepository.find(new TaxSpecificationBuilder().withEmployee(employee).build());

        return new PayslipBuilder()
                .withEmployee(employee)
                .withTax(tax)
                .build();
    }

    @Override
    public void savePayslips(List<Payslip> payslips) throws IOException {
        for (Payslip payslip : payslips) {
            save(payslip);
        }
    }

    @Override
    public void save(Payslip payslip) throws IOException {
        payslipWriter.write(payslip);

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
        taxRepository.close();
    }
}
