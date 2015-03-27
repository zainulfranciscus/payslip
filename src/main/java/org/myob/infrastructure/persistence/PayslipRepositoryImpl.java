package org.myob.infrastructure.persistence;

import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.employee.PayslipFactory;
import org.myob.domain.model.payslip.PayslipFactoryImpl;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxRepository;
import org.myob.service.PayslipWriter;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipRepositoryImpl implements PayslipRepository {

    private TaxRepository taxRepository;
    private PayslipWriter payslipWriter;

    @Override
    public Payslip find(TaxSpecification taxSpecification) throws IOException {

        Tax tax = taxRepository.find(taxSpecification);
        Employee employee = taxSpecification.employee();

        PayslipFactory payslipFactory = new PayslipFactoryImpl();
        return payslipFactory.createWith(employee.getPaymentStartDate(), employee.getPaymentEndDate(), employee, tax);
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
    public void save(Payslip payslip) throws IOException {
        payslipWriter.write(payslip);

    }
}
