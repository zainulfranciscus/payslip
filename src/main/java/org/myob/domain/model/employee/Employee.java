package org.myob.domain.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static org.myob.domain.model.employee.Payslip.TWELVE_MONTHS;
import static org.myob.domain.model.employee.Payslip.ZERO_ROUND_SCALE;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Employee {

    public static BigDecimal DIVISOR_FOR_SUPER_RATE = new BigDecimal(100);

    private final String firstName;
    private final String lastName;
    private final int salary;
    private final int aSuper;
    private final int paymentStartDate;
    private final int paymentStartingMonth;
    private final int paymentStartingYear;
    private final int endOfPaymentYear;
    private final int endOfPaymentMonth;
    private final int endOfPaymentDate;


    public Employee(String firstName,
                    String lastName,
                    int salary,
                    int aSuper,
                    int paymentStartDate,
                    int paymentStartingMonth,
                    int paymentStartingYear,
                    int endOfPaymentYear,
                    int endOfPaymentMonth,
                    int endOfPaymentDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.aSuper = aSuper;
        this.paymentStartDate = paymentStartDate;
        this.paymentStartingMonth = paymentStartingMonth;
        this.paymentStartingYear = paymentStartingYear;
        this.endOfPaymentYear = endOfPaymentYear;
        this.endOfPaymentMonth = endOfPaymentMonth;
        this.endOfPaymentDate = endOfPaymentDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return StringUtils.trimToEmpty(StringUtils.trimToEmpty(firstName) + " " + StringUtils.trimToEmpty(lastName));
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

    public BigDecimal grossIncomeAsBigDecimal(){
        return salaryAsBigDecimal().divide(TWELVE_MONTHS, ZERO_ROUND_SCALE, ROUND_HALF_UP);
    }

    public int getStartOfPaymentDate() {
        return this.paymentStartDate;
    }

    public int getStartOfPaymentMonth() {
        return this.paymentStartingMonth;
    }

    public int getStartOfPaymentYear() {
        return this.paymentStartingYear;
    }

    public int getEndOfPaymentYear() {
        return this.endOfPaymentYear;
    }

    public int getEndOfPaymentMonth() {
        return this.endOfPaymentMonth;
    }

    public int getEndOfPaymentDate() {
        return this.endOfPaymentDate;
    }

    public LocalDate getPaymentStartDate(){
        return new LocalDate(this.paymentStartingYear, this.paymentStartingMonth, this.paymentStartDate);
    }

    public LocalDate getPaymentEndDate() {
        return new LocalDate(this.endOfPaymentYear, this.endOfPaymentMonth, this.endOfPaymentDate);
    }
}
