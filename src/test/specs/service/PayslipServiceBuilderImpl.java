package service;

import org.myob.application.service.PayslipServiceBuilder;
import org.myob.infrastructure.repository.TaxRepository;
import org.myob.infrastructure.repository.impl.EmployeeRepositoryImpl;
import org.myob.infrastructure.repository.impl.TaxRepositoryImpl;
import org.myob.infrastructure.service.EmployeeRepository;

import java.io.FileNotFoundException;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipServiceBuilderImpl extends PayslipServiceBuilder {

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
