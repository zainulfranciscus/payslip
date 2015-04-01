package org.accounting.persistence.writer.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.accounting.model.payslip.Payslip;
import org.accounting.persistence.mapping.impl.PayslipHeader;
import org.accounting.persistence.writer.PayslipWriter;

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
        PayslipHeader [] payslipHeaderEnums = PayslipHeader.values();
        String [] headers = new String[payslipHeaderEnums.length];

        for(int i = 0; i <payslipHeaderEnums.length;i++){
            headers[i] = payslipHeaderEnums[i].toString();
        }
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
