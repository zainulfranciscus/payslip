package employee;

import payslip.MONTH;

import java.math.BigDecimal;

/**
 * Created by Lenovo on 26/03/2015.
 */
public interface EmployeePayslip {
    int ROUND_UP = BigDecimal.ROUND_UP;
    int ROUND_DOWN = BigDecimal.ROUND_DOWN;
    int ZERO_ROUND_SCALE = 0;

    String name();

    MONTH month();

    int getGrossIncome();

    BigDecimal grossIncomeAsBigDecimal();

    MONTH getMonth();

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
}