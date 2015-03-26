package payslip;

import payslip.MONTH;
import org.junit.Before;
import org.junit.Test;
import payslip.PayslipBuilder;

import static payslip.MONTH.OCTOBER;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipBuilderTest {
    private PayslipBuilder payslipBuilder;
    private MONTH expectedMonth;
    private int expectedGrossIncome;
    private int expectedIncomeTax;
    private int expectedNetIncome;
    private int expectedSuper;
    private String expectedEmployeeName;

    @Before
    public void setup(){
        payslipBuilder = new PayslipBuilder();
        expectedMonth = OCTOBER;
        expectedGrossIncome = 1000;
        expectedIncomeTax = 900;
        expectedNetIncome = 800;
        expectedSuper = 700;
        expectedEmployeeName = "Joe Blogg";
    }
    @Test
    public void shouldReturnPayslipWithOctoberAsPayPeriod(){
        assertEquals(expectedMonth, payslipBuilder.withMonth(expectedMonth).build().getMonth());
    }

    @Test
    public void shouldReturnPayslipWith1000AsGrossIncome(){
        assertEquals(expectedGrossIncome, payslipBuilder.withGrossIncome(expectedGrossIncome).build().getGrossIncome());
    }

    @Test
    public void shouldReturnPayslipWith900AsIncomeTax(){
        assertEquals(expectedIncomeTax, payslipBuilder.withIncomeTax(expectedIncomeTax).build().getIncomeTax());
    }

    @Test
    public void shouldReturnPayslipWith800AsNetIncome(){
        assertEquals(expectedNetIncome, payslipBuilder.withNetIncome(expectedNetIncome).build().getNetIncome());
    }

    @Test
    public void shouldReturnPayslipWith700AsSuper(){
        assertEquals(expectedSuper, payslipBuilder.withSuper(expectedSuper).build().getSuper());
    }

}
