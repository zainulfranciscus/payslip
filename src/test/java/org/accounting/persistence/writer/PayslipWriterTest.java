package org.accounting.persistence.writer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.accounting.model.payslip.Payslip;
import org.accounting.model.payslip.PayslipBuilder;
import org.accounting.persistence.mapping.impl.PayslipHeader;
import org.accounting.persistence.writer.impl.PayslipWriterImpl;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.accounting.persistence.writer.PayslipWriter.NEW_LINE;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipWriterTest {

    private static final String COMMA_DELIMITER = ",";
    private PayslipWriter payslipWriter;
    private StringWriter writer;

    @Before
    public void setup() {
        writer = new StringWriter();
        payslipWriter = new PayslipWriterImpl();
        payslipWriter.setWriter(writer);
    }

    @Test
    public void fullName_payPeriod_grossIncome_incomeTax_netIncome_super_shouldBeSeparatedByCommaAndEndedWithNewLine() throws IOException {

        Payslip payslip = new PayslipBuilder()
                .withGrossIncome(1000)
                .withIncomeTax(2000)
                .withName("Joe Blogg")
                .withNetIncome(3000)
                .withPayPeriod("01 March 2015 - 31 December 2015")
                .withSuper(20)
                .build();

        String expectedOutput = StringUtils.join(new String[]{payslip.getEmployeeName(),
                        payslip.getPayPeriod(),
                        String.valueOf(payslip.getGrossIncome()),
                        String.valueOf(payslip.getIncomeTax()),
                        String.valueOf(payslip.getNetIncome()),
                        String.valueOf(payslip.getSuper())},
                COMMA_DELIMITER).concat(NEW_LINE);


        payslipWriter.write(payslip);
        assertEquals(expectedOutput, writer.getBuffer().toString());

    }


    @Test
    public void shouldNotThrowAnExceptionUponClose() {

        boolean exceptionThrown = false;

        try {
            payslipWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown);
    }

    @Test
    public void shouldWritePayslipHeaders() throws IOException {

        String expectedOutput = StringUtils
                .join(PayslipHeader.values(),
                COMMA_DELIMITER)
                .concat(NEW_LINE);

        payslipWriter.writeHeader();

        assertEquals(expectedOutput,writer.getBuffer().toString());
    }

}
