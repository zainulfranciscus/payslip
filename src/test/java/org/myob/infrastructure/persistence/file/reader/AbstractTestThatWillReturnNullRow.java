package org.myob.infrastructure.persistence.file.reader;

import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class AbstractTestThatWillReturnNullRow extends AbstractCSVReaderTest {

    @Test
    public void rowReadFromCSVFileShouldBeNull(){
        assertNull(row);
    }
}
