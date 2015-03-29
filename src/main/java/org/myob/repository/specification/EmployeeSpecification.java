package org.myob.repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.myob.model.employee.Employee;
import org.myob.repository.specification.EmployeeSpecification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecification {

    private final int maxNumberOfEmployeesThatCanBeLoadedToMemory;
    private int numberOfEmployeesThatHasBeenLoadedToMemory;

    public EmployeeSpecification(int maxNumberOfEmployeesThatCanBeLoadedToMemory) {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = maxNumberOfEmployeesThatCanBeLoadedToMemory;
    }

    public int numberOfEmployeesLoadedToMemory() {
        return numberOfEmployeesThatHasBeenLoadedToMemory;
    }

    public void incrementNumberOfLineRead() {
        numberOfEmployeesThatHasBeenLoadedToMemory += 1;
    }

    public boolean hasReadTheAllowedNumberOfLines() {
        return numberOfEmployeesThatHasBeenLoadedToMemory != 0 && numberOfEmployeesThatHasBeenLoadedToMemory % maxNumberOfEmployeesThatCanBeLoadedToMemory == 0;
    }

    public void reset(){
        numberOfEmployeesThatHasBeenLoadedToMemory = 0;
    }

}
