package payslip;

import domain.Tax;
import employee.Employee;
import employee.EmployeePayslip;

import java.math.BigDecimal;

import static payslip.MONTH.numberOfMonthsAsBigDecimal;


/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipImpl implements EmployeePayslip {

    private final Employee employee;
    private final Tax tax;
    private final MONTH month;

    public EmployeePayslipImpl(MONTH month, Employee employee, Tax tax) {
        this.month = month;
        this.employee = employee;
        this.tax = tax;
    }

    @Override
    public String name() {
        return employee.getFullName();
    }

    @Override
    public MONTH month() {
       return month;
    }

    @Override
    public int getGrossIncome() {
        return grossIncomeAsBigDecimal().intValue();
    }

    @Override
    public BigDecimal grossIncomeAsBigDecimal(){
        return employee.salaryAsBigDecimal().divide(numberOfMonthsAsBigDecimal(),ZERO_ROUND_SCALE,ROUND_DOWN);
    }

    @Override
    public MONTH getMonth() {
        return month;
    }

    @Override
    public BigDecimal minIncomeAsBigDecimal(){
        return new BigDecimal(tax.getMinIncome());
    }


    @Override
    public BigDecimal getAmountOfTaxableIncome() {
        return employee.salaryAsBigDecimal().subtract(minIncomeAsBigDecimal());
    }

    @Override
    public BigDecimal taxPerDollarInBigDecimal() {
        return tax.taxDollarInCentsAsBigDecimal().divide(new BigDecimal(100));
    }

    @Override
    public BigDecimal amountOfTaxForEachTaxableDollar() {
        return getAmountOfTaxableIncome().multiply(taxPerDollarInBigDecimal());
    }

    @Override
    public BigDecimal taxOnSalary() {
        return amountOfTaxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
    }

    @Override
    public int getIncomeTax() {
        return incomeTaxAsBigDecimal().intValue();
    }

    @Override
    public BigDecimal incomeTaxAsBigDecimal(){
        return taxOnSalary().divide(numberOfMonthsAsBigDecimal(), ZERO_ROUND_SCALE, ROUND_UP).setScale(ZERO_ROUND_SCALE, ROUND_UP);
    }

    @Override
    public int netIncome() {
        return grossIncomeAsBigDecimal().subtract(incomeTaxAsBigDecimal()).intValue();
    }

    @Override
    public int getSuper() {
        return grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_DOWN).intValue();
    }

    @Override
    public String getEmployeeName() {
        return employee.getFullName();
    }
}
