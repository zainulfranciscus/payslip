package org.myob.employee;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface EmployeePayslip {

    final static int ROUND_UP = BigDecimal.ROUND_UP;
    final static int ROUND_DOWN = BigDecimal.ROUND_DOWN;
    final static int ZERO_ROUND_SCALE = 0;
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM YYYY");

    String paymentStartDate();

    String paymentEndDate();

    int getGrossIncome();

    BigDecimal grossIncomeAsBigDecimal();

    BigDecimal minIncomeAsBigDecimal();

    BigDecimal getAmountOfTaxableIncome();

    BigDecimal taxPerDollarInBigDecimal();

    BigDecimal amountOfTaxForEachTaxableDollar();

    BigDecimal taxOnSalary();

    int getIncomeTax();

    BigDecimal incomeTaxAsBigDecimal();

    int netIncome();

    int getSuper();

    String getEmployeeName();

    String payPeriod();
}
