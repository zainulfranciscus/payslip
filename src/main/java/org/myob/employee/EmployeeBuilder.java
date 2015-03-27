package org.myob.employee;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.myob.builder.Builder;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeBuilder implements Builder<Employee> {

    private String firstName;
    private String lastName;
    private int salary;
    private int aSuper;
    private int paymentStartDate;
    private int paymentStartingMonth;
    private int paymentStartingYear;
    private int endOfPaymentYear;
    private int endOfPaymentMonth;
    private int endOfPaymentDate;


    public EmployeeBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder withSuper(int aSuper) {
        this.aSuper = aSuper;
        return this;
    }

    public EmployeeBuilder withSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeBuilder withStartOfPaymentDate(int startDate) {
        this.paymentStartDate = startDate;
        return this;
    }


    public EmployeeBuilder withStartOfPaymentMonth(int startingMonth) {
        this.paymentStartingMonth = startingMonth;
        return this;
    }

    public EmployeeBuilder withStartOfPaymentYear(int startingYear) {
        this.paymentStartingYear = startingYear;
        return this;
    }

    public EmployeeBuilder withEndOfPaymentYear(int endOfPaymentYear) {
        this.endOfPaymentYear = endOfPaymentYear;
        return this;
    }

    public EmployeeBuilder withEndOfPaymentMonth(int endOfPaymentMonth) {
        this.endOfPaymentMonth = endOfPaymentMonth;
        return this;
    }

    public EmployeeBuilder withEndOfPaymentDate(int endOfPaymentDate) {
        this.endOfPaymentDate = endOfPaymentDate;
        return this;
    }

    public EmployeeBuilder withSuperRate(String superRate) {
        superRate = StringUtils.removePattern(superRate, "[^\\d+]");
        int superRateAsInt = NumberUtils.toInt(superRate);
        this.aSuper = superRateAsInt;
        return this;
    }

    public Employee build() {
        return new Employee(firstName,
                lastName,
                salary,
                aSuper,
                paymentStartDate,
                paymentStartingMonth,
                paymentStartingYear,
                endOfPaymentYear,
                endOfPaymentMonth,
                endOfPaymentDate);
    }
}
