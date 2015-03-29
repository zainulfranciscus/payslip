package org.myob.persistence.reader;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class FileReaderTypeTest {

    @Test
    public void shouldReturnANotWhenGivenAnExistingFile() throws FileNotFoundException {
        assertNotNull(FileReaderType.CLASSLOADER.getReader("employee/employee.csv"));
    }

    @Test
    public void shouldThrowAnExceptionWhenFileIsNotExist(){

        boolean exceptionThrown = false;

        try {
            FileReaderType.FILEREADER.getReader("emptyfile");
        }catch (Exception ex){
            exceptionThrown=true;
        }

        assertTrue(exceptionThrown);

    }
}
