package factory;

import builder.EmployeeBuilder;
import builder.TaxBuilder;
import domain.Employee;
import domain.EmployeePayslip;
import domain.Payslip;
import domain.Tax;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static domain.EmployeePayslip.ROUND_UP;
import static domain.EmployeePayslip.ZERO_ROUND_SCALE;
import static domain.Payslip.numberOfMonthsAsBigDecimal;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class EmployeePayslipFactoryTest {

    private static EmployeePayslip employeePayslip;
    private static Tax tax;
    private static Employee employee;
    private static Payslip.MONTH expectedMonth = Payslip.MONTH.OCTOBER;
    private static int baseTax;
    private static int maxIncome;
    private static int minIncome;
    private static int taxPerDollar;
    private static int salary;

    @BeforeClass
    public static void setup() {

        baseTax = 2000;
        maxIncome = 13000;
        minIncome = 7000;
        taxPerDollar = 100;
        salary = 12000;

        tax = new TaxBuilder().withBaseTax(baseTax).withMaxIncome(maxIncome).withMinIncome(minIncome).withTaxPerDollar(taxPerDollar).build();
        employee = new EmployeeBuilder().withFirstName("Joe").withSalary(salary).build();
        employeePayslip = EmployeePayslipFactory.createWith(expectedMonth, employee, tax);

    }

    @Test
    public void shouldReturnJoeAsPayslipName(){
        assertEquals(employee.getFullName(), employeePayslip.name());
    }

    @Test
    public void shouldReturnOctoberAsPaySlipMonth(){
        assertEquals(expectedMonth, employeePayslip.getMonth());
    }

    @Test
    public void shouldReturn1000AsGrossIncome(){
        assertEquals(salary/12, employeePayslip.getGrossIncome());
    }

    @Test
    public void taxableIncomeShouldBeSalaryMinusMinTaxableIncome(){
        BigDecimal expectedAmountOfTaxableIncome = employee.salaryAsBigDecimal().subtract(tax.minTaxableIncomeAsBigDecimal());
        assertEquals(expectedAmountOfTaxableIncome, employeePayslip.getAmountOfTaxableIncome());
    }

    @Test
    public void taxDollarShouldBeTaxDollarInCentsDividedBy100(){
        BigDecimal taxPerDollar = tax.taxDollarInCentsAsBigDecimal().divide(new BigDecimal(100));
        assertEquals(taxPerDollar, employeePayslip.taxPerDollarInBigDecimal());
    }

    @Test
    public void taxForEachTaxableDollarShouldBeAmountOfTaxableIncomeMultipliedByTaxDollar(){
        BigDecimal taxForEachTaxableDollar = employeePayslip.getAmountOfTaxableIncome().multiply(employeePayslip.taxPerDollarInBigDecimal());
        assertEquals(taxForEachTaxableDollar,employeePayslip.amountOfTaxForEachTaxableDollar());
    }

    @Test
    public void taxOnSalaryShouldBeTaxForEachTaxableDollarAddedByBaseTax(){
        BigDecimal taxOnSalary = employeePayslip.amountOfTaxForEachTaxableDollar().add(tax.baseTaxAsBigDecimal());
        assertEquals(taxOnSalary, employeePayslip.taxOnSalary());

    }

    @Test
    public void incomeTaxShouldBeTaxOnSalaryDividedByNumberOfMonthsRoundUpWith0Scale(){

        BigDecimal incomeTax = employeePayslip.taxOnSalary().divide(numberOfMonthsAsBigDecimal(), ZERO_ROUND_SCALE, ROUND_UP);

       assertEquals(incomeTax.intValue(),employeePayslip.getIncomeTax());

    }

    @Test
    public void netIncomeShouldBeGrossIncomeMinusIncomeTax(){
        assertEquals(employeePayslip.grossIncomeAsBigDecimal().subtract(employeePayslip.incomeTaxAsBigDecimal()).intValue(),employeePayslip.netIncome());

    }
}
