package repository;

import domain.Tax;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Criteria {

    boolean match(Tax tax);
}
