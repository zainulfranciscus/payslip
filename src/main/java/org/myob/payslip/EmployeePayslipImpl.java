package org.myob.payslip;

import org.joda.time.LocalDate;
import org.myob.employee.Employee;
import org.myob.employee.EmployeePayslip;
import org.myob.tax.Tax;

import java.math.BigDecimal;


/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipImpl implements EmployeePayslip {

    private final Employee employee;
    private final Tax tax;

    private LocalDate startPeriod;
    private LocalDate endPeriod;

    public EmployeePayslipImpl(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax) {

        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.employee = employee;
        this.tax = tax;
    }

    @Override
    public String payPeriod() {

        return paymentStartDate() + " " + paymentEndDate();
    }

    @Override
    public String paymentStartDate() {
        return formatter.print(startPeriod);
    }

    @Override
    public String paymentEndDate() {
        return formatter.print(endPeriod);
    }

    @Override
    public int getGrossIncome() {
        return grossIncomeAsBigDecimal().intValue();
    }

    @Override
    public BigDecimal grossIncomeAsBigDecimal(){
        return employee.salaryAsBigDecimal().divide(new BigDecimal(12),ZERO_ROUND_SCALE,ROUND_DOWN);
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
        return taxOnSalary().divide(new BigDecimal(12), ZERO_ROUND_SCALE, ROUND_UP).setScale(ZERO_ROUND_SCALE, ROUND_UP);
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
