package org.myob.repository.specification;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Specification<T> {

    boolean match(T t);
}
