package org.myob.model.payslip;

import org.junit.Test;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.model.tax.Tax;
import org.myob.model.tax.TaxBuilder;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipBuilderTest {

    @Test
    public void shouldReturnAPayslipWith(){

        PayslipBuilder factory = new PayslipBuilder();

        Employee employee = new EmployeeBuilder().build();
        Tax tax = new TaxBuilder().build();
        Payslip payslip = factory.withEmployee(employee).withTax(tax).build();
        assertNotNull(payslip);

    }
}
