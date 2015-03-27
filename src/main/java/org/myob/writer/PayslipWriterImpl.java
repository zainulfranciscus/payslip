package org.myob.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.myob.employee.EmployeePayslip;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public class PayslipWriterImpl implements PayslipWriter {

    private Writer writer;
    private CSVPrinter csvPrinter;

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(EmployeePayslip payslip) throws IOException {
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE));
        csvPrinter.printRecord(payslip.getEmployeeName(),
                payslip.payPeriod(),
                payslip.getGrossIncome(),
                payslip.getIncomeTax(),
                payslip.netIncome(),
                payslip.getSuper());

    }
}
