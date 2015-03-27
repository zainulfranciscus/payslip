package org.myob.domain.model.payslip;


import org.joda.time.LocalDate;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.infrastructure.repository.PayslipFactory;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipFactoryImpl implements PayslipFactory {

    @Override
    public Payslip createWith(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax) {
        return new PayslipImpl(startPeriod,endPeriod,employee,tax);
    }

}
