package org.myob.domain.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.myob.service.EmployeeSpecification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationImpl implements EmployeeSpecification{

    private final int maxNumberOfLinesShouldBeRead;
    private int lineNumberRead;

    public EmployeeSpecificationImpl(int numberOfLineRead, int maxNumberOfLinesShouldBeRead) {
        this.lineNumberRead = numberOfLineRead;
        this.maxNumberOfLinesShouldBeRead = maxNumberOfLinesShouldBeRead;
    }

    @Override
    public int numberOfLineRead() {
        return lineNumberRead;
    }

    @Override
    public void incrementNumberOfLineRead() {
        lineNumberRead += 1;
    }

    @Override
    public boolean hasReadTheAllowedNumberOfLines() {
        return lineNumberRead == maxNumberOfLinesShouldBeRead;
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
