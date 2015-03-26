package reader.impl;

import reader.Row;

import static reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeCsvRow extends Row {

    public EmployeeCsvRow(String firstName, String lastName, String paymentDate, String salary, String superRate) {
        put(FIRST_NAME,firstName);
        put(LAST_NAME,lastName);
        put(PAYMENT_DATE,paymentDate);
        put(ANNUAL_SALARY,salary);
        put(SUPER_RATE,superRate);
    }
}
