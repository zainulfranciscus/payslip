package domain;

import java.math.BigDecimal;

import static domain.Payslip.numberOfMonthsAsBigDecimal;


/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslip {

    public static final int ROUND_UP = BigDecimal.ROUND_UP;
    public static final int ROUND_DOWN = BigDecimal.ROUND_DOWN;
    public static final int ZERO_ROUND_SCALE = 0;

    private final Employee employee;
    private final Tax tax;
    private final Payslip.MONTH month;

    public EmployeePayslip(Payslip.MONTH month, Employee employee, Tax tax) {
        this.month = month;
        this.employee = employee;
        this.tax = tax;
    }

    public String name() {
        return employee.getFullName();
    }

    public Payslip.MONTH month() {
       return month;
    }

    public int getGrossIncome() {
        return grossIncomeAsBigDecimal().intValue();
    }

    public BigDecimal grossIncomeAsBigDecimal(){
        return employee.salaryAsBigDecimal().divide(numberOfMonthsAsBigDecimal(),ZERO_ROUND_SCALE,ROUND_DOWN);
    }

    public Payslip.MONTH getMonth() {
        return month;
    }

    public BigDecimal minIncomeAsBigDecimal(){
        return new BigDecimal(tax.getMinIncome());
    }


    public BigDecimal getAmountOfTaxableIncome() {
        return employee.salaryAsBigDecimal().subtract(minIncomeAsBigDecimal());
    }

    public BigDecimal taxPerDollarInBigDecimal() {
        return tax.taxDollarInCentsAsBigDecimal().divide(new BigDecimal(100));
    }

    public BigDecimal amountOfTaxForEachTaxableDollar() {
        return getAmountOfTaxableIncome().multiply(taxPerDollarInBigDecimal());
    }

    public BigDecimal taxOnSalary() {
        return amountOfTaxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
    }

    public int getIncomeTax() {
        return incomeTaxAsBigDecimal().intValue();
    }

    public BigDecimal incomeTaxAsBigDecimal(){
        return taxOnSalary().divide(numberOfMonthsAsBigDecimal(), ZERO_ROUND_SCALE, ROUND_UP).setScale(ZERO_ROUND_SCALE, ROUND_UP);
    }

    public int netIncome() {
        return grossIncomeAsBigDecimal().subtract(incomeTaxAsBigDecimal()).intValue();
    }

    public int getSuper() {
        return grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_DOWN).intValue();
    }
}
