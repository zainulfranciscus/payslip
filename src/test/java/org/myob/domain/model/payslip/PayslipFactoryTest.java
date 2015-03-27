package org.myob.domain.model.payslip;

import org.joda.time.LocalDate;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.domain.model.tax.Tax;
import org.junit.BeforeClass;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.infrastructure.repository.PayslipFactory;

import java.math.BigDecimal;

import static org.myob.domain.model.payslip.PayslipImpl.ROUND_DOWN;
import static org.myob.domain.model.payslip.PayslipImpl.ROUND_UP;
import static org.myob.domain.model.payslip.PayslipImpl.ZERO_ROUND_SCALE;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipFactoryTest {

    private static Payslip payslip;
    private static Tax tax;
    private static Employee employee;
    private static LocalDate startPeriod;
    private static LocalDate endPeriod;


    @BeforeClass
    public static void setup() {

        tax = new TaxBuilder().withBaseTax(2000).withMaxIncome(13000).withMinIncome(7000).withTaxPerDollar(100).build();
        employee = new EmployeeBuilder().withFirstName("Joe").withLastName("Blogg").withSalary(12000).build();

        startPeriod = new LocalDate(2015, 01, 01);
        endPeriod = new LocalDate(2015, 10, 31);

        PayslipFactory factory = new PayslipFactoryImpl();
        payslip = factory.createWith(startPeriod, endPeriod, employee, tax);

    }

    @Test
    public void shouldReturnJoeAsPayslipName() {
        assertEquals(employee.getFullName(), payslip.getEmployeeName());
    }

    @Test
    public void grossIncomeShouldBeSalaryDividedByMonthsInAYear() {
        assertEquals(employee.salaryAsBigDecimal().divide(new BigDecimal(12), ZERO_ROUND_SCALE, ROUND_DOWN).intValue(), payslip.getGrossIncome());
    }

    @Test
    public void taxableIncomeShouldBeSalaryMinusMinTaxableIncome() {
        BigDecimal expectedAmountOfTaxableIncome = employee.salaryAsBigDecimal().subtract(tax.minTaxableIncomeAsBigDecimal());
        assertEquals(expectedAmountOfTaxableIncome, payslip.getAmountOfTaxableIncome());
    }

    @Test
    public void taxDollarShouldBeTaxDollarInCentsDividedBy100() {
        BigDecimal taxPerDollar = tax.taxDollarInCentsAsBigDecimal().divide(new BigDecimal(100));
        assertEquals(taxPerDollar, payslip.taxPerDollarInBigDecimal());
    }

    @Test
    public void taxForEachTaxableDollarShouldBeAmountOfTaxableIncomeMultipliedByTaxDollar() {
        BigDecimal taxForEachTaxableDollar = payslip.getAmountOfTaxableIncome().multiply(payslip.taxPerDollarInBigDecimal());
        assertEquals(taxForEachTaxableDollar, payslip.amountOfTaxForEachTaxableDollar());
    }

    @Test
    public void taxOnSalaryShouldBeTaxForEachTaxableDollarAddedByBaseTax() {
        BigDecimal taxOnSalary = payslip.amountOfTaxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
        assertEquals(taxOnSalary, payslip.taxOnSalary());

    }

    @Test
    public void incomeTaxShouldBeTaxOnSalaryDividedByNumberOfMonthsRoundUpWith0Scale() {
        BigDecimal incomeTax = payslip.taxOnSalary().divide(new BigDecimal(12), ZERO_ROUND_SCALE, ROUND_UP);
        assertEquals(incomeTax.intValue(), payslip.getIncomeTax());
    }

    @Test
    public void netIncomeShouldBeGrossIncomeMinusIncomeTax() {
        assertEquals(payslip.grossIncomeAsBigDecimal().subtract(payslip.incomeTaxAsBigDecimal()).intValue(), payslip.netIncome());
    }

    @Test
    public void superShouldBeGrossIncomeTimesSuperRate() {
        assertEquals(payslip.grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_DOWN).intValue(), payslip.getSuper());
    }

    @Test
    public void shouldReturnStartPeriodDateMonthAndYear() {
        assertEquals(Payslip.formatter.print(startPeriod), payslip.paymentStartDate());
    }

    @Test
    public void shouldReturnEndPeriodDateMonthAndYear() {
        assertEquals(Payslip.formatter.print(endPeriod), payslip.paymentEndDate());
    }

}
