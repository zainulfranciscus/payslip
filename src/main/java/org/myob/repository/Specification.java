package org.myob.repository;

import org.myob.tax.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Specification<T> {

    boolean match(T t);
}
