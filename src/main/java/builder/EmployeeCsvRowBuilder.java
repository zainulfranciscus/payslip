package builder;

import reader.Row;
import reader.impl.EmployeeCsvRow;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRowBuilder {
    private String firstName;
    private String lastName;
    private String salary;
    private String superRate;
    private String paymentDate;

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

    public Row build() {
        return new EmployeeCsvRow(this.firstName,this.lastName,this.paymentDate,this.salary,this.superRate);
    }
}
