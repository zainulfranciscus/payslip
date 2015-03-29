package org.myob.persistence.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.myob.model.payslip.Payslip;

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
    public void write(Payslip payslip) throws IOException {
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE));
        csvPrinter.printRecord(payslip.getEmployeeName(),
                payslip.getPayPeriod(),
                payslip.getGrossIncome(),
                payslip.getIncomeTax(),
                payslip.getNetIncome(),
                payslip.getSuper());
        csvPrinter.flush();
        csvPrinter.close();

    }
}
