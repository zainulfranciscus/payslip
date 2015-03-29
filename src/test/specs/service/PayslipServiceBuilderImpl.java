package service;

import org.myob.repository.PayslipRepository;
import org.myob.repository.impl.PayslipRepositoryImpl;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.EmployeeRepositoryImpl;
import org.myob.repository.impl.TaxRepositoryImpl;
import org.myob.repository.EmployeeRepository;

import java.io.FileNotFoundException;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipServiceBuilderImpl extends AbstractPayslipServiceBuilder {


    @Override
    public TaxRepository createTaxRepository() throws FileNotFoundException {

        TaxRepository taxRepository = new TaxRepositoryImpl();
        taxRepository.setReader(this.taxReader);
        return taxRepository;
    }

    @Override
    public EmployeeRepository createEmployeeRepository() throws FileNotFoundException {

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.setReader(this.employeeReader);
        return employeeRepository;
    }
}
