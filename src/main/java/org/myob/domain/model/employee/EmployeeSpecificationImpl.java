package org.myob.domain.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.myob.domain.service.EmployeeSpecification;

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

    @Override
    public boolean match(Employee employee) {

        boolean exceptionForGettingPaymentStartDate = false;

        try {
            employee.getPaymentStartDate();
        }catch(Exception ex){
            exceptionForGettingPaymentStartDate = true;
        }

        boolean exceptionForGettingPaymentEndDate = false;

        try{
            employee.getPaymentEndDate();
        }catch(Exception ex){
            exceptionForGettingPaymentEndDate = true;
        }
        return StringUtils.isNotBlank(employee.getFullName()) && !exceptionForGettingPaymentStartDate && !exceptionForGettingPaymentEndDate;
    }
}
