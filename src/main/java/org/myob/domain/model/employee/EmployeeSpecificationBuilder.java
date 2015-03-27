package org.myob.domain.model.employee;

import org.myob.service.EmployeeSpecification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilder {

    private int numberOfLineRead;
    private int maxNumberOfLinesShouldBeRead;

    public EmployeeSpecificationBuilder withLineNumberOfRead(int numberOfLineRead) {
        this.numberOfLineRead = numberOfLineRead;
        return this;
    }

    public EmployeeSpecificationBuilder withMaxNumberOfLinesShouldBeRead(int maxNumberOfLineRead) {
        this.maxNumberOfLinesShouldBeRead = maxNumberOfLineRead;
        return this;
    }

    public EmployeeSpecification build(){
        return new EmployeeSpecificationImpl(numberOfLineRead,maxNumberOfLinesShouldBeRead);
    }
}
