package org.myob.payslip;

import org.myob.payslip.PayslipBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipTest {

    private Payslip payslip;
    private int expectedGrossIncome = 1000;
    private int expectedIncomeTax = 900;
    private int expectedNetIncome = 800;
    private int expectedSuper = 700;

    @Before
    public void setup() {
        PayslipBuilder builder = new PayslipBuilder();
        payslip = builder.withGrossIncome(expectedGrossIncome).withIncomeTax(expectedIncomeTax).withNetIncome(expectedNetIncome).withSuper(expectedSuper).build();

    }

    @Test
    public void shouldBe1000AsGrossIncome() {
        assertEquals(expectedGrossIncome, payslip.getGrossIncome());
    }

    @Test
    public void shouldBe900AsIncomeTax() {
        assertEquals(expectedIncomeTax, payslip.getIncomeTax());
    }

    @Test
    public void shouldBe800ForNetIncome() {
        assertEquals(expectedNetIncome, payslip.getNetIncome());
    }

    @Test
    public void shouldBe700ForSuper() {
        assertEquals(expectedSuper, payslip.getSuper());
    }
}
