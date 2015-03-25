package repository.impl;

import domain.Tax;
import repository.Criteria;

/**
 * Created by Lenovo on 26/03/2015.
 */
public class TaxCriteria implements Criteria {

    private int salary;

    public TaxCriteria(int salary){
        this.salary = salary;
    }

    @Override
    public boolean match(Tax tax) {
        return tax.getMinIncome() <= salary && tax.getMaxIncome() >= salary;
    }
}
