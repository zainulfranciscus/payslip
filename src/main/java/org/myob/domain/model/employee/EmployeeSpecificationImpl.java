package org.myob.domain.model.employee;

import org.apache.commons.lang3.StringUtils;
import org.myob.infrastructure.persistence.Specification;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class EmployeeSpecificationImpl implements Specification<Employee>{

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
