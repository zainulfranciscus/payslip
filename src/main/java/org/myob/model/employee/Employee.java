package org.myob.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static org.myob.repository.PayslipCalculator.TWELVE_MONTHS;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Employee {

    public static final BigDecimal DIVISOR_FOR_SUPER_RATE = new BigDecimal(100);
    public static final int ZERO_ROUND_SCALE = 0;

    private final String firstName;
    private final String lastName;
    private final double salary;
    private final double aSuper;
    private final LocalDate startPaymentDate;
    private final LocalDate endPaymentDate;

    public Employee(String firstName,
                    String lastName,
                    double salary,
                    double aSuper,
                    LocalDate startPaymentDate,
                    LocalDate endPaymentDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.aSuper = aSuper;
        this.startPaymentDate = startPaymentDate;
        this.endPaymentDate = endPaymentDate;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return StringUtils.trimToEmpty(String.format("%s %s",StringUtils.trimToEmpty(firstName),StringUtils.trimToEmpty(lastName)));
    }

    public double getSalary() {
        return salary;
    }

    public double getSuper() {
        return aSuper;
    }

    public BigDecimal getSuperAsBigDecimal() {
        return new BigDecimal(aSuper);
    }

    public BigDecimal getSuperRate() {
        return getSuperAsBigDecimal().divide(DIVISOR_FOR_SUPER_RATE);
    }

    public BigDecimal salaryAsBigDecimal() {
        return new BigDecimal(getSalary());
    }

    public BigDecimal grossIncomeAsBigDecimal() {
        return salaryAsBigDecimal().divide(TWELVE_MONTHS, ZERO_ROUND_SCALE, ROUND_HALF_UP);
    }

    public LocalDate getPaymentStartDate() {
        return startPaymentDate;
    }

    public LocalDate getPaymentEndDate() {
        return endPaymentDate;
    }
}
