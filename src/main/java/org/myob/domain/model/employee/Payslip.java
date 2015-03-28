package org.myob.domain.model.employee;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Payslip {

    static final int ROUND_UP = BigDecimal.ROUND_UP;
    static final int ROUND_DOWN = BigDecimal.ROUND_DOWN;
    static final int ZERO_ROUND_SCALE = 0;
    static final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM YYYY");
    static final BigDecimal DIVISOR_TO_CONVERT_CENTS_TO_DOLLAR = new BigDecimal(100);
    static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    static final BigDecimal ZERO_TAX = new BigDecimal(0) ;

    String getPaymentStartDate();

    String getPaymentEndDate();

    int getGrossIncome();

    int getIncomeTax();

    int getNetIncome();

    int getSuper();

    String getEmployeeName();

    String getPayPeriod();



    BigDecimal minIncomeAsBigDecimal();

    BigDecimal getAmountOfTaxableIncome();

    BigDecimal taxPerDollarInBigDecimal();

    BigDecimal taxForEachTaxableDollar();

    BigDecimal taxOnSalary();

    BigDecimal incomeTaxAsBigDecimal();

}
