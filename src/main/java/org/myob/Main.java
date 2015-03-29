package org.myob;

import asg.cliche.CLIException;
import asg.cliche.Command;
import asg.cliche.ShellFactory;
import org.apache.commons.lang3.StringUtils;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.service.PayslipService;
import org.myob.service.builder.PayslipServiceBuilder;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class Main {

    private static final String DEFAULT_TAX_TABLE = "ATO_tax_table.csv";
    String employeeFileName;
    String taxFileName;
    String payslipFileName;

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("Payslip", "", new Main()).commandLoop();
    }

    @Command(name = "employee", abbrev = "e", description = "specify an absolute path to an employee csv file")
    public void employeeFileName(String employee) throws CLIException {
        this.employeeFileName = employee;
    }

    @Command(name = "tax", abbrev = "t", description = "specify an absolute path to a tax csv file")
    public void taxFileName(String tax) throws CLIException {
        this.taxFileName = tax;
    }

    @Command(name = "payslip", abbrev = "p", description = "specify an absolute path to a payslip csv file")
    public void payslipFileName(String payslip) throws CLIException {
        this.payslipFileName = payslip;
    }

    @Command(name = "check", abbrev = "c", description = "display the employee file name, tax, and payslip that you have entered")
    public void check() {

        System.out.println("Employee filename : [" + employeeFileName + "]");
        System.out.println("Tax filename : [" + taxFileName + "]");
        System.out.println("Payslip filename : [" + payslipFileName + "]");
    }

    @Command(name = "writePayslip", abbrev = "w", description = "display the employee file name, tax, and payslip that you have entered")
    public void writePayslips() throws Exception {

        PayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilder();
        payslipServiceBuilder.withEmployeeFileName(employeeFileName);
        payslipServiceBuilder.withPayslipFileName(payslipFileName);

        String taxFile = StringUtils.isBlank(taxFileName) ? loadFromClassPath(DEFAULT_TAX_TABLE) : taxFileName;
        payslipServiceBuilder.withTaxFileName(taxFile);

        PayslipService payslipService = payslipServiceBuilder.build();
        payslipService.writePayslips(new EmployeeSpecification());
        payslipService.close();

    }

    public String loadFromClassPath(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
