package org.myob.reader.employee;

import org.junit.Test;
import org.myob.reader.AbstractCSVReaderTest;
import org.myob.reader.impl.EmployeeCSVFileReaderImpl;
import org.myob.repository.Reader;

import static org.junit.Assert.assertEquals;
import static org.myob.reader.EmployeeHeader.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class CSVReaderTestForEmployeeCSVFiles extends AbstractCSVReaderTest {

    @Test
    public void firstNameShouldBeJoe(){
        assertEquals("Joe",row.get(FIRST_NAME));
    }

    @Test
    public void lastNameShouldBeBlogg(){
        assertEquals("Blogg",row.get(LAST_NAME));
    }

    @Test
    public void salaryShouldBe12000(){
        assertEquals("12000",row.get(ANNUAL_SALARY));
    }

    @Test
    public void superShouldBe10Percent(){
        assertEquals("10%",row.get(SUPER_RATE));
    }

    @Test
    public void paymentDateShouldBeMarch(){
        assertEquals("01 March – 31 March",row.get(PAYMENT_DATE));

    }

    @Override
    public Reader readerForCSV() {
        return new EmployeeCSVFileReaderImpl( "employee/employee.csv");
    }
}
