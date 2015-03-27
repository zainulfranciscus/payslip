package org.myob.infrastructure.persistence.file.reader.impl;

import org.myob.infrastructure.persistence.file.reader.EmployeeHeader;
import org.myob.infrastructure.persistence.file.reader.Row;

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

        put(EmployeeHeader.FIRST_NAME,firstName);
        put(EmployeeHeader.LAST_NAME,lastName);
        put(EmployeeHeader.PAYMENT_DATE,paymentDate);
        put(EmployeeHeader.ANNUAL_SALARY,salary);
        put(EmployeeHeader.SUPER_RATE,superRate);
        put(EmployeeHeader.START_PAYMENT_DATE, startOfPaymentDate);
        put(EmployeeHeader.START_PAYMENT_MONTH, startOfPaymentMonth);
        put(EmployeeHeader.START_PAYMENT_YEAR, startOfPaymentYear);
        put(EmployeeHeader.END_PAYMENT_DATE, endOfPaymentDate);
        put(EmployeeHeader.END_PAYMENT_MONTH, endOfPaymentMonth);
        put(EmployeeHeader.END_PAYMENT_YEAR, endOfPaymentYear);

    }
}
