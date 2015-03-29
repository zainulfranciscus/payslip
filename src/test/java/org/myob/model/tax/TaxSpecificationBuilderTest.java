package org.myob.model.tax;

import org.junit.Before;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.junit.Test;
import org.myob.repository.specification.Specification;
import org.myob.repository.specification.TaxSpecificationBuilder;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

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
        Specification<Tax> taxCriteria = taxSpecificationBuilder.withEmployee(employeeBuilder.withSalary(12000).build()).build();
        assertTrue(taxCriteria.match(taxBuilder.withMinIncome(1000).withMaxIncome(20000).build()));
    }

    @Test
    public void shouldNotMatchTaxBecauseSalaryIsAboveMaxIncome(){
        Specification<Tax> taxCriteria = taxSpecificationBuilder.withEmployee(employeeBuilder.withSalary(15000).build()).build();
        assertFalse(taxCriteria.match(taxBuilder.withMinIncome(500).withMaxIncome(1000).build()));
    }

    @Test
    public void shouldMatchTheCriteriaEmployeePayPeriodFallsWithinTaxStartDate(){

        Employee employee = employeeBuilder.withSalary(10000)
                .withStartOfPaymentDate(2)
                .withStartOfPaymentMonth(4)
                .withStartOfPaymentYear(2015)
                .build();

        Tax tax = taxBuilder.withMinIncome(1000)
                .withMaxIncome(20000)
                .withStartingDay(1)
                .withStartingMonth(4)
                .withStartingYear(2015)
                .build();

        Specification<Tax> taxCriteria = taxSpecificationBuilder.withEmployee(employee).build();
        assertTrue(taxCriteria.match(tax));
    }



}

