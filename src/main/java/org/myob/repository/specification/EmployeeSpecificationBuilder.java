package org.myob.repository.specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationBuilder {

    private int maxNumberOfEmployeesThatCanBeLoadedToMemory = 10;

    public EmployeeSpecificationBuilder withMaxNumberOfEmployeesThatCanBePutIntoMemory(int maxNumberOfLineRead) {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = maxNumberOfLineRead;
        return this;
    }

    public EmployeeSpecification build(){
        return new EmployeeSpecificationImpl(maxNumberOfEmployeesThatCanBeLoadedToMemory);
    }
}
