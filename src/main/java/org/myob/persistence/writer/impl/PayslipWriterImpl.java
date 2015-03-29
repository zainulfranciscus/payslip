package org.myob.persistence.writer.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.myob.model.payslip.Payslip;
import org.myob.persistence.mapping.impl.PayslipHeader;
import org.myob.persistence.writer.PayslipWriter;

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
        csvPrinter = makePrinter();
        csvPrinter.printRecord(payslip.getEmployeeName(),
                payslip.getPayPeriod(),
                payslip.getGrossIncome(),
                payslip.getIncomeTax(),
                payslip.getNetIncome(),
                payslip.getSuper());

    }

    @Override
    public void writeHeader() throws IOException {
        csvPrinter = makePrinter();
        String [] headers = PayslipHeader.getHeaderLabel();
        csvPrinter.printRecord(headers);

    }

    private CSVPrinter makePrinter() throws IOException {
        return  new CSVPrinter(writer, CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE));
    }

    public void close() throws IOException {
        writer.flush();
        writer.close();


    }
}
