package org.myob.persistence.row;

import org.joda.time.LocalDate;

import static org.myob.persistence.mapping.impl.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRow extends Row {

    public EmployeeCsvRow(String firstName,
                          String lastName,
                          String paymentDate,
                          String salary,
                          String superRate,
                          String startOfPaymentDate,
                          String startOfPaymentMonth,
                          String startOfPaymentYear,
                          String endOfPaymentDate,
                          String endOfPaymentMonth,
                          String endOfPaymentYear) {

        put(FIRST_NAME, firstName);
        put(LAST_NAME, lastName);
        put(ANNUAL_SALARY, salary);
        put(SUPER_RATE, superRate);
        put(START_PAYMENT_DATE, startOfPaymentDate);
        put(START_PAYMENT_MONTH, startOfPaymentMonth);
        put(START_PAYMENT_YEAR, startOfPaymentYear);
        put(END_PAYMENT_DATE, endOfPaymentDate);
        put(END_PAYMENT_MONTH, endOfPaymentMonth);
        put(END_PAYMENT_YEAR, endOfPaymentYear);

    }

    public LocalDate getPaymentStartDate() {
        return toLocalDate(get(START_PAYMENT_DATE), get(START_PAYMENT_MONTH), get(START_PAYMENT_YEAR));
    }

    public LocalDate getPaymentEndDate() {
        return toLocalDate(get(END_PAYMENT_DATE), get(END_PAYMENT_MONTH), get(END_PAYMENT_YEAR));
    }

    public boolean isPaymentDatesInFebruary() {
        return getPaymentStartDate().getMonthOfYear() == 2 && getPaymentEndDate().getMonthOfYear() == 2;
    }


    public boolean arePaymentDatesInTheSameYear() {
        return getPaymentStartDate().getYear() == getPaymentEndDate().getYear();
    }

    public boolean paymentDatesInALeapYear() {
        return arePaymentDatesInTheSameYear() && getPaymentStartDate().year().isLeap();

    }

    public boolean paymentDatesAreWithinTheSameMonth() {
        if (isPaymentDatesInFebruary() && paymentDatesInALeapYear()) {
            return getPaymentEndDate().getDayOfMonth() == 29 && getPaymentStartDate().getDayOfMonth() == 1;
        }

        return  getPaymentStartDate().dayOfMonth().getMinimumValue() == getPaymentStartDate().getDayOfMonth() &&
                getPaymentStartDate().dayOfMonth().getMaximumValue() == getPaymentEndDate().getDayOfMonth() &&
                getPaymentEndDate().getMonthOfYear() == getPaymentStartDate().getMonthOfYear() &&
                arePaymentDatesInTheSameYear();
    }




}
