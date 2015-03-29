package org.myob.model.tax;

import org.junit.Before;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.junit.Test;

import org.myob.repository.specification.TaxSpecificationBuilder;
import org.myob.repository.specification.TaxSpecification;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.myob.repository.specification.TaxSpecification.NO_MAXIMUM_INCOME;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxSpecificationBuilderTest {

    private TaxSpecificationBuilder taxSpecificationBuilder;
    private TaxBuilder taxBuilder;
    private EmployeeBuilder employeeBuilder;

    @Before
    public void setup(){

        taxSpecificationBuilder = new TaxSpecificationBuilder();

        employeeBuilder = new EmployeeBuilder()
                .withEndPaymentPeriod(2015, 12, 31)
                .withStartPaymentPeriod(2015, 1, 1);

        taxBuilder = new TaxBuilder().withStartPeriod(2015,1,1);

    }

    @Test
    public void shouldBeTrueBecauseSalaryIsBetweenMinIncomeAndMaxIncome(){
        TaxSpecification taxCriteria = taxSpecificationBuilder.withEmployee(employeeBuilder.withSalary(12000).build()).build();
        assertTrue(taxCriteria.match(taxBuilder
                .withMinIncome(1000).withMaxIncome(20000).build()));
    }

    @Test
    public void shouldNotMatchTaxBecauseSalaryIsAboveMaxIncome(){
        TaxSpecification taxCriteria = taxSpecificationBuilder.withEmployee(employeeBuilder.withSalary(15000).build()).build();
        assertFalse(taxCriteria.match(taxBuilder.withMinIncome(500).withMaxIncome(1000).build()));
    }

    @Test
    public void shouldMatchThisSalaryBecauseTaxHasNoMaximumIncome()
    {
        Tax tax = taxBuilder.withMinIncome(1000).withMaxIncome(NO_MAXIMUM_INCOME).build();
        Employee employee = employeeBuilder.withSalary(20000).build();
        TaxSpecification taxCriteria = taxSpecificationBuilder.withEmployee(employee).build();
        assertTrue(taxCriteria.match(tax));
    }

    @Test
    public void shouldMatchTheCriteriaEmployeePayPeriodFallsWithinTaxStartDate(){

        Employee employee = employeeBuilder
                .withStartPaymentPeriod(2015, 1, 1)
                .build();

        Tax tax = taxBuilder.withStartPeriod(2015, 1, 1).build();

        TaxSpecification taxCriteria = taxSpecificationBuilder.withEmployee(employee).build();
        assertTrue(taxCriteria.match(tax));
    }



}

