package org.myob.repository.specification;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilderTest {
    private EmployeeSpecificationBuilder employeeSpecificationBuilder = new EmployeeSpecificationBuilder();

    @Test
    public void numberOfEmployeesLoadedToMemoryShouldBe0(){
        assertEquals(0, employeeSpecificationBuilder.build().numberOfEmployeesLoadedToMemory());
    }

    @Test
    public void hasReadTheAllowedNumberOfLinesIsTrue_BecauseThereCanOnlyBe1EmployeeLoadedIntoMemory(){

        EmployeeSpecification employeeSpecification = employeeSpecificationBuilder
                .withMaxNumberOfEmployeesThatCanBePutIntoMemory(1)
                .build();
        employeeSpecification.incrementNumberOfLineRead();
        assertTrue(employeeSpecification.hasReadTheAllowedNumberOfLines());
    }


}
