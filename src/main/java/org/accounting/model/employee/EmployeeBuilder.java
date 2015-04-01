package org.accounting.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.LocalDate;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeBuilder {

    private String firstName;
    private String lastName;
    private double salary;
    private double aSuper;
    private LocalDate paymentStartPeriod;
    private LocalDate paymentEndPeriod;


    public EmployeeBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder withSuper(double aSuper) {
        this.aSuper = aSuper;
        return this;
    }

    public EmployeeBuilder withSalary(double salary) {
        this.salary = salary;
        return this;
    }


    public EmployeeBuilder withSuperRate(String superRate) {
        superRate = StringUtils.replace(superRate, "%", "");
        this.aSuper = NumberUtils.toDouble(superRate);
        return this;
    }

    public EmployeeBuilder withStartPaymentPeriod(int year, int month, int date){
        this.paymentStartPeriod = new LocalDate(year,month,date);
        return this;
    }

    public EmployeeBuilder withEndPaymentPeriod(int year, int month, int date){
        this.paymentEndPeriod = new LocalDate(year,month,date);
        return this;
    }
    public Employee build() {
        return new Employee(firstName,
                lastName,
                salary,
                aSuper,
                paymentStartPeriod,
                paymentEndPeriod);
    }
}
