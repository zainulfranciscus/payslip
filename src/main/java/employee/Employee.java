package employee;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Employee {

    public static BigDecimal DIVISOR_FOR_SUPER_RATE = new BigDecimal(100);

    private final String firstName;
    private final String lastName;
    private final int salary;
    private final int aSuper;

    public Employee(String firstName, String lastName, int salary, int aSuper) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.aSuper = aSuper;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {

        return firstName + " " + lastName;
    }

    public int getSalary() {
        return salary;
    }


    public int getSuper() {
        return aSuper;
    }

    public BigDecimal getSuperAsBigDecimal(){
        return new BigDecimal(aSuper);
    }

    public BigDecimal getSuperRate() {
        return getSuperAsBigDecimal().divide(DIVISOR_FOR_SUPER_RATE);
    }

    public BigDecimal salaryAsBigDecimal(){
        return new BigDecimal(getSalary());
    }

}
