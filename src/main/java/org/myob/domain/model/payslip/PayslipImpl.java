package org.myob.domain.model.payslip;

import org.joda.time.LocalDate;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.tax.Tax;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;


/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipImpl implements Payslip {

    private final Employee employee;
    private final Tax tax;

    private LocalDate startPeriod;
    private LocalDate endPeriod;

    public PayslipImpl(LocalDate startPeriod, LocalDate endPeriod, Employee employee, Tax tax) {

        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.employee = employee;
        this.tax = tax;
    }

    @Override
    public String getPayPeriod() {

        return getPaymentStartDate() + " - " + getPaymentEndDate();
    }

    @Override
    public String getPaymentStartDate() {
        return formatter.print(startPeriod);
    }

    @Override
    public String getPaymentEndDate() {
        return formatter.print(endPeriod);
    }

    @Override
    public int getGrossIncome() {
        return employee.grossIncomeAsBigDecimal().intValue();
    }

    @Override
    public BigDecimal minIncomeAsBigDecimal(){
        if(tax == null){
            return new BigDecimal(0);
        }
        return new BigDecimal(tax.getTaxPerDollarOver());
    }


    @Override
    public BigDecimal getAmountOfTaxableIncome() {
        if(minIncomeAsBigDecimal().intValue() == 0){
            return ZERO_TAX;
        }
        return employee.salaryAsBigDecimal().subtract(minIncomeAsBigDecimal());
    }

    @Override
    public BigDecimal taxPerDollarInBigDecimal() {
        if(tax == null){
            return ZERO_TAX;
        }
        return tax.taxDollarInCentsAsBigDecimal().divide(DIVISOR_TO_CONVERT_CENTS_TO_DOLLAR);
    }

    @Override
    public BigDecimal taxForEachTaxableDollar() {
        return getAmountOfTaxableIncome().multiply(taxPerDollarInBigDecimal());
    }

    @Override
    public BigDecimal taxOnSalary() {
        if(tax == null){
            return ZERO_TAX;
        }
        return taxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
    }

    @Override
    public int getIncomeTax() {
        return incomeTaxAsBigDecimal().intValue();
    }

    @Override
    public BigDecimal incomeTaxAsBigDecimal(){
        return taxOnSalary().divide(TWELVE_MONTHS, ZERO_ROUND_SCALE, ROUND_HALF_UP).setScale(ZERO_ROUND_SCALE, ROUND_HALF_UP);
    }

    @Override
    public int getNetIncome() {
        return employee.grossIncomeAsBigDecimal().subtract(incomeTaxAsBigDecimal()).intValue();
    }

    @Override
    public int getSuper() {
        return employee.grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_HALF_UP).intValue();
    }

    @Override
    public String getEmployeeName() {
        return employee.getFullName();
    }
}
