package org.myob.infrastructure.persistence;

import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxRepository;
import org.myob.service.PayslipWriter;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface PayslipRepository {

    Payslip find(TaxSpecification taxSpecification) throws IOException;

    void setTaxRepository(TaxRepository taxRepository);

    void setWriter(PayslipWriter payslipWriter);

    void save(Payslip payslip) throws IOException;

}
