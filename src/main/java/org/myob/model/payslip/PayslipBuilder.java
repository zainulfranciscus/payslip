package org.myob.model.payslip;


import org.myob.model.employee.Employee;
import org.myob.model.tax.Tax;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipBuilder {

    private Employee employee;
    private Tax tax;

    public PayslipBuilder withEmployee(Employee employee){
        this.employee = employee;
        return this;
    }

    public PayslipBuilder withTax(Tax tax){
        this.tax = tax;
        return this;
    }

    public Payslip build(){
        return new Payslip(employee,tax);
    }

}
