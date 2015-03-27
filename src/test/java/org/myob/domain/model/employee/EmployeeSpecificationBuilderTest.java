package org.myob.domain.model.employee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilderTest {
    private EmployeeSpecificationBuilder employeeSpecificationBuilder = new EmployeeSpecificationBuilder();

    @Test
    public void numberOfLineReadShouldBe10(){
        assertEquals(10, employeeSpecificationBuilder.withLineNumberOfRead(10).build().numberOfLineRead());
    }
}
