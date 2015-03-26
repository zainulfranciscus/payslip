package org.myob.payslip;

import org.joda.time.LocalDate;
import org.myob.tax.TaxBuilder;
import org.myob.tax.Tax;
import org.junit.BeforeClass;
import org.junit.Test;
import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.myob.employee.EmployeePayslip;
import org.myob.employee.EmployeePayslipFactory;

import java.math.BigDecimal;

import static org.myob.payslip.EmployeePayslipImpl.ROUND_DOWN;
import static org.myob.payslip.EmployeePayslipImpl.ROUND_UP;
import static org.myob.payslip.EmployeePayslipImpl.ZERO_ROUND_SCALE;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactoryTest {

    private static EmployeePayslip employeePayslip;
    private static Tax tax;
    private static Employee employee;
    private static LocalDate startPeriod;
    private static LocalDate endPeriod;


    @BeforeClass
    public static void setup() {

        tax = new TaxBuilder().withBaseTax(2000).withMaxIncome(13000).withMinIncome(7000).withTaxPerDollar(100).build();
        employee = new EmployeeBuilder().withFirstName("Joe").withSalary(12000).build();

        startPeriod = new LocalDate(2015, 01, 01);
        endPeriod = new LocalDate(2015, 10, 31);

        EmployeePayslipFactory factory = new EmployeePayslipFactoryImpl();
        employeePayslip = factory.createWith(startPeriod, endPeriod, employee, tax);

    }

    @Test
    public void shouldReturnJoeAsPayslipName() {
        assertEquals(employee.getFullName(), employeePayslip.getEmployeeName());
    }

    @Test
    public void grossIncomeSholdBeSalaryDividedByMonthsInAYear() {
        assertEquals(employee.salaryAsBigDecimal().divide(new BigDecimal(12), ZERO_ROUND_SCALE, ROUND_DOWN).intValue(), employeePayslip.getGrossIncome());
    }

    @Test
    public void taxableIncomeShouldBeSalaryMinusMinTaxableIncome() {
        BigDecimal expectedAmountOfTaxableIncome = employee.salaryAsBigDecimal().subtract(tax.minTaxableIncomeAsBigDecimal());
        assertEquals(expectedAmountOfTaxableIncome, employeePayslip.getAmountOfTaxableIncome());
    }

    @Test
    public void taxDollarShouldBeTaxDollarInCentsDividedBy100() {
        BigDecimal taxPerDollar = tax.taxDollarInCentsAsBigDecimal().divide(new BigDecimal(100));
        assertEquals(taxPerDollar, employeePayslip.taxPerDollarInBigDecimal());
    }

    @Test
    public void taxForEachTaxableDollarShouldBeAmountOfTaxableIncomeMultipliedByTaxDollar() {
        BigDecimal taxForEachTaxableDollar = employeePayslip.getAmountOfTaxableIncome().multiply(employeePayslip.taxPerDollarInBigDecimal());
        assertEquals(taxForEachTaxableDollar, employeePayslip.amountOfTaxForEachTaxableDollar());
    }

    @Test
    public void taxOnSalaryShouldBeTaxForEachTaxableDollarAddedByBaseTax() {
        BigDecimal taxOnSalary = employeePayslip.amountOfTaxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
        assertEquals(taxOnSalary, employeePayslip.taxOnSalary());

    }

    @Test
    public void incomeTaxShouldBeTaxOnSalaryDividedByNumberOfMonthsRoundUpWith0Scale() {
        BigDecimal incomeTax = employeePayslip.taxOnSalary().divide(new BigDecimal(12), ZERO_ROUND_SCALE, ROUND_UP);
        assertEquals(incomeTax.intValue(), employeePayslip.getIncomeTax());
    }

    @Test
    public void netIncomeShouldBeGrossIncomeMinusIncomeTax() {
        assertEquals(employeePayslip.grossIncomeAsBigDecimal().subtract(employeePayslip.incomeTaxAsBigDecimal()).intValue(), employeePayslip.netIncome());
    }

    @Test
    public void superShouldBeGrossIncomeTimesSuperRate() {
        assertEquals(employeePayslip.grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_DOWN).intValue(), employeePayslip.getSuper());
    }

    @Test
    public void shouldReturnStartPeriodDateMonthAndYear() {
        assertEquals(EmployeePayslip.formatter.print(startPeriod), employeePayslip.paymentStartDate());
    }

    @Test
    public void shouldReturnEndPeriodDateMonthAndYear() {
        assertEquals(EmployeePayslip.formatter.print(endPeriod), employeePayslip.paymentEndDate());
    }

}
