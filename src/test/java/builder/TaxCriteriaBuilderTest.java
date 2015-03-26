package builder;

import employee.EmployeeBuilder;
import org.junit.Test;
import repository.impl.TaxCriteria;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class TaxCriteriaBuilderTest {

    private TaxCriteriaBuilder builder = new TaxCriteriaBuilder();

    @Test
    public void shouldCreateATaxCriteriaThatMatchesEmployeeWithSalaryOf12000(){
        TaxCriteria taxCriteria =builder.withEmployee(new EmployeeBuilder().withSalary(12000).build()).build();
        assertTrue(taxCriteria.match(new TaxBuilder().withMinIncome(1000).withMaxIncome(20000).build()));

    }

}

