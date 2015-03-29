package org.myob.persistence.writer;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.model.employee.Employee;
import org.myob.model.employee.EmployeeBuilder;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.model.tax.Tax;
import org.myob.model.tax.TaxBuilder;
import org.myob.service.PayslipCalculator;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.myob.persistence.writer.PayslipWriter.NEW_LINE;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipWriterTest {

    private static final String COMMA_DELIMITER = ",";
    private PayslipWriter payslipWriter;
    private StringWriter writer;
    private Employee employee;
    private Tax tax;
    private String firstName;
    private String lastName;
    private LocalDate firstJan2015;
    private LocalDate thirty1stDec2015;
    private int salary;
    private int superRate;

    @Before
    public void setup() {

        firstName = "Joe";
        lastName = "blogg";
        firstJan2015 = new LocalDate(2015, 01, 01);
        thirty1stDec2015 = new LocalDate(2015, 12, 31);
        salary = 12000;
        superRate = 10;

        employee = new EmployeeBuilder()
                .withEndPaymentPeriod(thirty1stDec2015.getYear(),thirty1stDec2015.getMonthOfYear(), thirty1stDec2015.getDayOfMonth())
                .withStartPaymentPeriod(firstJan2015.getYear(), firstJan2015.getMonthOfYear(), firstJan2015.getDayOfMonth())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withSalary(salary)
                .withSuper(superRate).build();

        tax = new TaxBuilder().withBaseTax(2000)
                .withTaxPerDollar(30)
                .withMaxIncome(10000)
                .withMinIncome(0)
                .withStartingDay(firstJan2015.getDayOfMonth())
                .withStartingMonth(firstJan2015.getMonthOfYear())
                .withStartingYear(firstJan2015.getYear())
                .build();

    }

    @Test
    public void fullName_payPeriod_grossIncome_incomeTax_netIncome_super_shouldBeSeparatedByCommaAndEndedWithNewLine() throws IOException {

        writer = new StringWriter();

        payslipWriter = new PayslipWriterImpl();
        payslipWriter.setWriter(writer);


        Payslip payslip = new PayslipBuilder()
                .withGrossIncome(1000)
                .withIncomeTax(2000)
                .withName("Joe Blogg")
                .withNetIncome(3000)
                .withPayPeriod("01 March 2015 - 31 December 2015")
                .withSuper(20)
                .build();

        String expectedOutput = payslip.getEmployeeName()
                .concat(COMMA_DELIMITER)
                .concat(payslip.getPayPeriod())
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getGrossIncome()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getIncomeTax()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getNetIncome()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getSuper()))
                .concat(NEW_LINE);

        payslipWriter.write(payslip);

        assertEquals(expectedOutput, writer.getBuffer().toString());

    }

}
