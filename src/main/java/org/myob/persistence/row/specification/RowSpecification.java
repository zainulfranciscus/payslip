package org.myob.persistence.row.specification;

import org.myob.persistence.row.Row;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public interface RowSpecification<T extends Row> {
   boolean isValid(T row);
}
