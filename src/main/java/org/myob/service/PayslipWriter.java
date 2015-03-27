package org.myob.service;

import org.myob.domain.model.employee.Payslip;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Zainul Franciscus on 27/03/2015.
 */
public interface PayslipWriter {
    String NEW_LINE = "\n";

    void setWriter(Writer writer);

    void write(Payslip payslip) throws IOException;
}
