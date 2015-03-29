package org.myob.repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.myob.model.employee.Employee;
import org.myob.repository.specification.EmployeeSpecification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationImpl implements EmployeeSpecification{

    private final int maxNumberOfEmployeesThatCanBeLoadedToMemory;
    private int numberOfEmployeesThatHasBeenLoadedToMemory;

    public EmployeeSpecificationImpl(int maxNumberOfEmployeesThatCanBeLoadedToMemory) {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = maxNumberOfEmployeesThatCanBeLoadedToMemory;
    }

    @Override
    public int numberOfEmployeesLoadedToMemory() {
        return numberOfEmployeesThatHasBeenLoadedToMemory;
    }

    @Override
    public void incrementNumberOfLineRead() {
        numberOfEmployeesThatHasBeenLoadedToMemory += 1;
    }

    @Override
    public boolean hasReadTheAllowedNumberOfLines() {
        return numberOfEmployeesThatHasBeenLoadedToMemory != 0 &&  numberOfEmployeesThatHasBeenLoadedToMemory % maxNumberOfEmployeesThatCanBeLoadedToMemory == 0;
    }

}
