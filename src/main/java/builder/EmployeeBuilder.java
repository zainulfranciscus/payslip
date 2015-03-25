package builder;

import domain.Employee;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeeBuilder implements Builder<Employee>{

    private String firstName;
    private String lastName;
    private int salary;
    private int aSuper;


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

    public Employee build() {
        return new Employee(firstName,lastName,salary,aSuper);
    }

}
