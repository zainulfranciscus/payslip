package org.accounting.repository;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.accounting.model.employee.Employee;
import org.accounting.model.tax.Tax;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;


/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipCalculator {

    public static final String DATE_FORMAT_DD_MMMM_YYYY = "dd MMMM YYYY";
    public static final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT_DD_MMMM_YYYY);
    public static final BigDecimal DIVISOR_TO_CONVERT_CENTS_TO_DOLLAR = new BigDecimal(100);
    public static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    public static final BigDecimal ZERO_TAX = new BigDecimal(0) ;

    private final Employee employee;
    private final Tax tax;

    public PayslipCalculator(Employee employee, Tax tax) {
        this.employee = employee;
        this.tax = tax;
    }


    public String getPayPeriod() {
        return String.format("%s - %s",getPaymentStartDate(),getPaymentEndDate());
    }

    public String getPaymentStartDate() {
        return formatter.print(employee.getPaymentStartDate());
    }


    public String getPaymentEndDate() {
        return formatter.print(employee.getPaymentEndDate());
    }


    public int getGrossIncome() {
        return employee.grossIncomeAsBigDecimal().intValue();
    }


    public BigDecimal minIncomeAsBigDecimal(){
        if(tax == null){
            return ZERO_TAX;
        }
        return new BigDecimal(tax.getTaxPerDollarOver());
    }

    public BigDecimal getAmountOfTaxableIncome() {
        if(minIncomeAsBigDecimal().intValue() == 0){
            return ZERO_TAX;
        }
        return employee.salaryAsBigDecimal().subtract(minIncomeAsBigDecimal());
    }

    public BigDecimal taxPerDollarInBigDecimal() {
        if(tax == null){
            return ZERO_TAX;
        }
        return tax.taxDollarInCentsAsBigDecimal().divide(DIVISOR_TO_CONVERT_CENTS_TO_DOLLAR);
    }

    public BigDecimal taxForEachTaxableDollar() {
        return getAmountOfTaxableIncome().multiply(taxPerDollarInBigDecimal());
    }

    public BigDecimal taxOnSalary() {
        if(tax == null){
            return ZERO_TAX;
        }
        return taxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
    }

    public int getIncomeTax() {
        return incomeTaxAsBigDecimal().intValue();
    }

    public BigDecimal incomeTaxAsBigDecimal(){
        return taxOnSalary().divide(TWELVE_MONTHS, Employee.ZERO_ROUND_SCALE, ROUND_HALF_UP);
    }

    public int getNetIncome() {
        return employee.grossIncomeAsBigDecimal().subtract(incomeTaxAsBigDecimal()).intValue();
    }

    public int getSuper() {
        return employee.grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(Employee.ZERO_ROUND_SCALE, ROUND_HALF_UP).intValue();
    }

    public String getEmployeeName() {
        return employee.getFullName();
    }


}
