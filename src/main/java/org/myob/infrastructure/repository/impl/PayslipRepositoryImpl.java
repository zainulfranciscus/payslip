package org.myob.infrastructure.repository.impl;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.tax.TaxSpecificationBuilder;
import org.myob.infrastructure.repository.PayslipWriter;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.PayslipFactory;
import org.myob.domain.model.payslip.PayslipFactoryImpl;
import org.myob.domain.model.tax.Tax;
import org.myob.infrastructure.service.PayslipRepository;

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
        for(Employee employee:employees){
            payslips.add(create(employee));
        }

        return payslips;
    }


    @Override
    public Payslip create(Employee employee) throws Exception {

        Tax tax = taxRepository.find( new TaxSpecificationBuilder().withEmployee(employee).build());
        PayslipFactory payslipFactory = new PayslipFactoryImpl();
        return payslipFactory.createWith(employee.getPaymentStartDate(), employee.getPaymentEndDate(), employee, tax);
    }

    @Override
    public void savePayslips(List<Payslip> payslips) throws IOException {
        for(Payslip payslip:payslips){
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

}
