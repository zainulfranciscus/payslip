package org.myob.tax;

import org.junit.Before;
import org.myob.employee.Employee;
import org.myob.employee.EmployeeBuilder;
import org.junit.Test;
import org.myob.repository.impl.TaxCriteria;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCriteriaBuilderTest {

    private TaxCriteriaBuilder taxCriteriaBuilder;
    private TaxBuilder taxBuilder;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup(){

        taxCriteriaBuilder = new TaxCriteriaBuilder();

        employeeBuilder = new EmployeeBuilder()
                .withEndOfPaymentDate(31)
                .withEndOfPaymentMonth(12)
                .withEndOfPaymentYear(2015)
                .withStartOfPaymentDate(1)
                .withStartOfPaymentMonth(1)
                .withStartOfPaymentYear(2015);

        taxBuilder = new TaxBuilder()
                .withStartingDay(1)
                .withStartingMonth(1)
                .withStartingYear(2015);


    }

    @Test
    public void shouldBeTrueBecauseSalaryIsBetweenMinIncomeAndMaxIncome(){
        TaxCriteria taxCriteria = taxCriteriaBuilder.withEmployee(employeeBuilder.withSalary(12000).build()).build();
        assertTrue(taxCriteria.match(taxBuilder.withMinIncome(1000).withMaxIncome(20000).build()));
    }

    @Test
    public void shouldNotMatchTaxBecauseSalaryIsAboveMaxIncome(){
        TaxCriteria taxCriteria = taxCriteriaBuilder.withEmployee(employeeBuilder.withSalary(15000).build()).build();
        assertFalse(taxCriteria.match(taxBuilder.withMinIncome(500).withMaxIncome(1000).build()));
    }

    @Test
    public void shouldMatchTheCriteriaEmployeePayPeriodFallsWithinTaxStartDate(){

        Employee employee = employeeBuilder.withSalary(10000).build();

        Tax tax = taxBuilder.withMinIncome(1000)
                .withMaxIncome(20000)
                .withStartingDay(2)
                .withStartingMonth(3)
                .withStartingYear(2015)
                .build();

        TaxCriteria taxCriteria = taxCriteriaBuilder.withEmployee(employee).build();
        assertTrue(taxCriteria.match(tax));
    }



}

