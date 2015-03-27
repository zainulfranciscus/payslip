package org.myob.domain.model.employee;

import org.myob.domain.service.EmployeeSpecification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilder {


    private int maxNumberOfEmployeesThatCanBeLoadedToMemory;


    public EmployeeSpecificationBuilder withMaxNumberOfEmployeesThatCanBePutIntoMemory(int maxNumberOfLineRead) {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = maxNumberOfLineRead;
        return this;
    }

    public EmployeeSpecification build(){
        return new EmployeeSpecificationImpl(maxNumberOfEmployeesThatCanBeLoadedToMemory);
    }
}
