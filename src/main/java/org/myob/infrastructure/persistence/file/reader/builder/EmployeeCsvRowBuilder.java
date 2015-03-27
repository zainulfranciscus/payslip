package org.myob.infrastructure.persistence.file.reader.builder;

import org.myob.infrastructure.persistence.file.reader.Row;
import org.myob.infrastructure.persistence.file.reader.impl.EmployeeCsvRow;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRowBuilder {
    private String firstName;
    private String lastName;
    private String salary;
    private String superRate;
    private String paymentDate;
    private String startOfPaymentDate;
    private String startOfPaymentMonth;
    private String startOfPaymentYear;
    private String endOfPaymentDate;
    private String endOfPaymentMonth;
    private String endOfPaymentYear;

    public EmployeeCsvRowBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeCsvRowBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeCsvRowBuilder withSalary(String salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeCsvRowBuilder withSuper(String superRate) {
        this.superRate = superRate;
        return this;
    }

    public EmployeeCsvRowBuilder withPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public EmployeeCsvRowBuilder withStartOfPaymentDate(String startOfPaymentDate) {
        this.startOfPaymentDate = startOfPaymentDate;
        return this;
    }

    public EmployeeCsvRowBuilder withStartOfPaymentMonth(String startOfPaymentMonth) {
        this.startOfPaymentMonth = startOfPaymentMonth;
        return this;
    }

    public EmployeeCsvRowBuilder withStartOfPaymentYear(String startOfPaymentYear) {
        this.startOfPaymentYear = startOfPaymentYear;
        return this;
    }

    public EmployeeCsvRowBuilder withEndOfPaymentDate(String endOfPaymentDate) {
        this.endOfPaymentDate = endOfPaymentDate;
        return this;
    }

    public EmployeeCsvRowBuilder withEndOfPaymentMonth(String endOfPaymentMonth) {
        this.endOfPaymentMonth = endOfPaymentMonth;
        return this;
    }

    public EmployeeCsvRowBuilder withEndOfPaymentYear(String endOfPaymentYear) {
        this.endOfPaymentYear = endOfPaymentYear;
        return this;
    }

    public Row build() {
        return new EmployeeCsvRow(this.firstName,
                this.lastName,
                this.paymentDate,
                this.salary,
                this.superRate,
                this.startOfPaymentDate,
                this.startOfPaymentMonth,
                this.startOfPaymentYear,
                this.endOfPaymentDate,
                this.endOfPaymentMonth,
                this.endOfPaymentYear);
    }
}
