package org.myob.infrastructure.persistence.file.writer;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.myob.domain.model.employee.Employee;
import org.myob.domain.model.employee.EmployeeBuilder;
import org.myob.domain.model.employee.Payslip;
import org.myob.domain.model.payslip.PayslipFactoryImpl;
import org.myob.domain.model.tax.Tax;
import org.myob.domain.model.tax.TaxBuilder;
import org.myob.infrastructure.repository.PayslipWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.myob.infrastructure.repository.PayslipWriter.NEW_LINE;

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

        employee = new EmployeeBuilder().withEndOfPaymentDate(thirty1stDec2015.getDayOfMonth())
                .withEndOfPaymentMonth(thirty1stDec2015.getMonthOfYear())
                .withEndOfPaymentYear((thirty1stDec2015.getYear()))
                .withStartOfPaymentDate(firstJan2015.getDayOfMonth())
                .withStartOfPaymentMonth(firstJan2015.getMonthOfYear())
                .withStartOfPaymentYear(firstJan2015.getYear())
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


        Payslip payslip = new PayslipFactoryImpl().createWith(firstJan2015, thirty1stDec2015, employee, tax);

        String expectedOutput = payslip.getEmployeeName()
                .concat(COMMA_DELIMITER)
                .concat(payslip.payPeriod())
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getGrossIncome()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getIncomeTax()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.netIncome()))
                .concat(COMMA_DELIMITER)
                .concat(String.valueOf(payslip.getSuper()))
                .concat(NEW_LINE);

        payslipWriter.write(payslip);

        assertEquals(expectedOutput,writer.getBuffer().toString());

    }

    @Test
    public void test() throws IOException {
        payslipWriter = new PayslipWriterImpl();
        payslipWriter.setWriter(new FileWriter("E:\\output.csv",true));

        Payslip payslip = new PayslipFactoryImpl().createWith(firstJan2015, thirty1stDec2015, employee, tax);
        payslipWriter.write(payslip);
    }


}
