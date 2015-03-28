package org.myob.domain.model.payslip;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.infrastructure.repository.PayslipFactory;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.myob.domain.model.employee.Payslip.*;
import static org.myob.domain.model.employee.Payslip.DIVISOR_FOR_TAX_PER_DOLLAR;
import static org.myob.domain.model.employee.Payslip.TWELVE_MONTHS;
import static org.myob.domain.model.payslip.PayslipImpl.*;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipFactoryTest {

    private Payslip payslip;
    private Tax tax;
    private Employee employee;
    private LocalDate startPeriod;
    private LocalDate endPeriod;


    @Before
    public void setup() {
        startPeriod = new LocalDate(2015, 01, 01);
        endPeriod = new LocalDate(2015, 10, 31);
        employee = new EmployeeBuilder().withFirstName("Joe").withLastName("Blogg").withSalary(12000).build();

    }

    class AssertThat {
        AssertThat nameOnPayslipShouldBe(String name) {
            assertEquals(name, payslip.getEmployeeName());
            return this;
        }

        public AssertThat grossIncomeShouldBe(int grossIncome) {
            assertEquals(grossIncome, payslip.getGrossIncome());
            return this;
        }

        public AssertThat taxableIncomeShouldBe(BigDecimal taxableIncome) {
            assertEquals(taxableIncome, payslip.getAmountOfTaxableIncome());
            return this;
        }

        public AssertThat taxOnEachDollarShouldBe(BigDecimal taxOnEachDollar) {
            assertEquals(taxOnEachDollar, payslip.taxPerDollarInBigDecimal());
            return this;
        }

        public AssertThat taxForEachTaxableDollarShouldBe(BigDecimal taxForEachTaxableDollar) {
            assertEquals(taxForEachTaxableDollar, payslip.taxForEachTaxableDollar());
            return this;
        }

        public AssertThat taxOnSalaryShouldBe(BigDecimal taxOnSalary) {
            assertEquals(taxOnSalary, payslip.taxOnSalary());
            return this;
        }

        public AssertThat incomeTaxShouldBe(int incomeTax) {
            assertEquals(incomeTax, payslip.getIncomeTax());
            return this;
        }

        public AssertThat netIncomeShouldBe(int netIncome) {
            assertEquals(netIncome, payslip.getNetIncome());
            return this;
        }

        public AssertThat superShouldBe(int aSuper) {
            assertEquals(aSuper, payslip.getSuper());
            return this;
        }

        public AssertThat startPeriodShouldBe(String startPeriod) {
            assertEquals(startPeriod, payslip.getPaymentStartDate());
            return this;
        }

        public AssertThat endPeriodShouldBe(String endPeriod) {
            assertEquals(endPeriod, payslip.getPaymentEndDate());
            return this;
        }
    }

    @Test
    public void shouldHave_Name_TaxableIncome_TaxOnEachDollar_TaxForEachTaxableIncome_TaxOnSalary_IncomeTax_NetIncome_super_startAndEndPeriod() {
        tax = new TaxBuilder().withBaseTax(2000).withMaxIncome(13000).withMinIncome(7000).withTaxPerDollar(100).build();

        PayslipFactory factory = new PayslipFactoryImpl();
        payslip = factory.createWith(startPeriod, endPeriod, employee, tax);

        AssertThat assertThat = new AssertThat();
        assertThat.nameOnPayslipShouldBe(employee.getFullName())
                .grossIncomeShouldBe(salaryDividedBy12Months())
                .taxableIncomeShouldBe(employee.salaryAsBigDecimal().subtract(tax.minTaxableIncomeAsBigDecimal()))
                .taxOnEachDollarShouldBe(tax.taxDollarInCentsAsBigDecimal().divide(DIVISOR_FOR_TAX_PER_DOLLAR))
                .taxForEachTaxableDollarShouldBe(payslip.getAmountOfTaxableIncome().multiply(payslip.taxPerDollarInBigDecimal()))
                .taxOnSalaryShouldBe(payslip.taxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal()))
                .incomeTaxShouldBe(payslip.taxOnSalary().divide(TWELVE_MONTHS, ZERO_ROUND_SCALE, ROUND_UP).intValue())
                .netIncomeShouldBe(employee.grossIncomeAsBigDecimal().subtract(payslip.incomeTaxAsBigDecimal()).intValue())
                .superShouldBe(employee.grossIncomeAsBigDecimal().multiply(employee.getSuperRate()).setScale(ZERO_ROUND_SCALE, ROUND_DOWN).intValue())
                .startPeriodShouldBe(formatter.print(startPeriod))
                .endPeriodShouldBe(formatter.print(endPeriod));
    }

    @Test
    public void shouldHave0Tax_BecauseTaxIsNull() {
        PayslipFactory factory = new PayslipFactoryImpl();
        payslip = factory.createWith(startPeriod, endPeriod, employee, tax);

        AssertThat assertThat = new AssertThat();

        assertThat.nameOnPayslipShouldBe(employee.getFullName())
                .grossIncomeShouldBe(salaryDividedBy12Months())
                .taxableIncomeShouldBe(ZERO_TAX)
                .taxOnEachDollarShouldBe(ZERO_TAX)
                .taxOnSalaryShouldBe(ZERO_TAX);
    }

    private int salaryDividedBy12Months() {
        return employee.salaryAsBigDecimal().divide(TWELVE_MONTHS, ZERO_ROUND_SCALE, ROUND_DOWN).intValue();
    }


}
