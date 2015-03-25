package domain;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslip {

    public static final int ROUND_UP = BigDecimal.ROUND_UP;
    public static final int ZERO_ROUND_SCALE = 0;

    private Employee employee;
    private Tax tax;
    private Payslip.MONTH month;

    public EmployeePayslip(Payslip.MONTH month, Employee employee, Tax tax) {
        this.month = month;
        this.employee = employee;
        this.tax = tax;
    }

    public String name() {
        return employee.getFullName();
    }

    public Payslip.MONTH month() {
       return null;
    }

    public int getGrossIncome() {
        return employee.getSalary()/12;
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
        return taxOnSalary().divide(new BigDecimal(Payslip.MONTH.values().length)).setScale(ZERO_ROUND_SCALE,ROUND_UP).intValue();
    }
}
