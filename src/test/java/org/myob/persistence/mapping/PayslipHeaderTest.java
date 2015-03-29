package org.myob.persistence.mapping;

import org.junit.Before;
import org.junit.Test;
import org.myob.persistence.mapping.impl.PayslipHeader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipHeaderTest {

    private String [] headers;

    @Before
    public void setup(){
        headers = PayslipHeader.getHeaderLabel();
    }
    @Test
    public void headerShouldBeEquivalentToEnumLength(){
        assertEquals(headers.length, PayslipHeader.values().length);

    }

    @Test
    public void headersShouldHaveTheSameLabelAsTheEnum(){
        for(int i = 0; i < headers.length; i++){
            assertEquals(PayslipHeader.values()[i].getLabel(),headers[i]);
        }
    }
}
