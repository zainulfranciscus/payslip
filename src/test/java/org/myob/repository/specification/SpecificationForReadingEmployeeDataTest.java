package org.myob.repository.specification;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class SpecificationForReadingEmployeeDataTest {

    @Test
    public void numberOfEmployeesLoadedToMemoryShouldBe0(){
        assertEquals(0, new SpecificationForReadingEmployeeData().numberOfEmployeesLoadedToMemory());
    }

    @Test
    public void hasReadTheAllowedNumberOfLinesIsTrue_BecauseThereCanOnlyBe1EmployeeLoadedIntoMemory(){

        SpecificationForReadingEmployeeData specificationForReadingEmployeeData = new SpecificationForReadingEmployeeData(1);
        specificationForReadingEmployeeData.incrementNumberOfEmployeeLoadedToMemory();

        assertTrue(specificationForReadingEmployeeData.hasLoadTheAllowedNumberOfEmployeesToMemory());
    }


}
