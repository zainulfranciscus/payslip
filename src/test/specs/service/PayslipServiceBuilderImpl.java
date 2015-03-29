package service;

import org.myob.service.builder.PayslipServiceBuilder;
import org.myob.repository.TaxRepository;
import org.myob.repository.impl.EmployeeRepositoryImpl;
import org.myob.repository.impl.TaxRepositoryImpl;
import org.myob.repository.EmployeeRepository;

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
