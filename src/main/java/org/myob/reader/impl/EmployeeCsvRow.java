package org.myob.reader.impl;

import org.myob.reader.Row;

import static org.myob.reader.EmployeeHeader.*;

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

        put(FIRST_NAME,firstName);
        put(LAST_NAME,lastName);
        put(PAYMENT_DATE,paymentDate);
        put(ANNUAL_SALARY,salary);
        put(SUPER_RATE,superRate);
        put(START_PAYMENT_DATE, startOfPaymentDate);
        put(START_PAYMENT_MONTH, startOfPaymentMonth);
        put(START_PAYMENT_YEAR, startOfPaymentYear);
        put(END_PAYMENT_DATE, endOfPaymentDate);
        put(END_PAYMENT_MONTH, endOfPaymentMonth);
        put(END_PAYMENT_YEAR, endOfPaymentYear);

    }
}
