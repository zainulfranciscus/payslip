package org.myob.domain.model.employee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilderTest {
    private EmployeeSpecificationBuilder employeeSpecificationBuilder = new EmployeeSpecificationBuilder();

    @Test
    public void numberOfLineReadShouldBe0(){
        assertEquals(0, employeeSpecificationBuilder.build().numberOfEmployeesLoadedToMemory());
    }
}
