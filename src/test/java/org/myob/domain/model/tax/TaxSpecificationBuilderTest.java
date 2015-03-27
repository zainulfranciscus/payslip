package org.myob.domain.model.tax;

import org.junit.Before;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.domain.model.tax.TaxSpecificationBuilder;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.junit.Test;
import org.myob.infrastructure.persistence.Specification;

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

        Employee employee = employeeBuilder.withSalary(10000).build();

        Tax tax = taxBuilder.withMinIncome(1000)
                .withMaxIncome(20000)
                .withStartingDay(2)
                .withStartingMonth(3)
                .withStartingYear(2015)
                .build();

        Specification<Tax> taxCriteria = taxSpecificationBuilder.withEmployee(employee).build();
        assertTrue(taxCriteria.match(tax));
    }



}

