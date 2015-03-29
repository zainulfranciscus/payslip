package org.myob.repository.specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class SpecificationForReadingEmployeeData {

    private final int maxNumberOfEmployeesThatCanBeLoadedToMemory;

    private int numberOfEmployeesThatHasBeenLoadedToMemory;

    public SpecificationForReadingEmployeeData() {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = 10;
    }

    public SpecificationForReadingEmployeeData(int maxNumberOfEmployeesThatCanBeLoadedToMemory) {
        this.maxNumberOfEmployeesThatCanBeLoadedToMemory = maxNumberOfEmployeesThatCanBeLoadedToMemory;
    }

    public int numberOfEmployeesLoadedToMemory() {
        return numberOfEmployeesThatHasBeenLoadedToMemory;
    }

    public void incrementNumberOfEmployeeLoadedToMemory() {
        numberOfEmployeesThatHasBeenLoadedToMemory += 1;
    }

    public boolean hasLoadTheAllowedNumberOfEmployeesToMemory() {
        return numberOfEmployeesThatHasBeenLoadedToMemory != 0 && numberOfEmployeesThatHasBeenLoadedToMemory % maxNumberOfEmployeesThatCanBeLoadedToMemory == 0;
    }

    public void reset(){
        numberOfEmployeesThatHasBeenLoadedToMemory = 0;
    }

}
