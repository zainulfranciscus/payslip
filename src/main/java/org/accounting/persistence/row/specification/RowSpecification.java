package org.accounting.persistence.row.specification;

import org.accounting.persistence.row.Row;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public interface RowSpecification<T extends Row> {
   boolean isValid(T row);
}
