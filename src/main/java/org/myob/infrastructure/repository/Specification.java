package org.myob.infrastructure.repository;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Specification<T> {

    boolean match(T t);
}
