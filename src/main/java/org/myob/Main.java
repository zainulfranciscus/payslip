package org.myob;

import asg.cliche.CLIException;
import asg.cliche.Command;
import asg.cliche.ShellFactory;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.service.PayslipService;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.service.builder.impl.PayslipServiceBuilderImpl;

import java.io.IOException;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class Main {

    String employeeFileName;
    String taxFileName;
    String payslipFileName;

    public static void main(String[]args) throws IOException {
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
    public void check(){

        System.out.println("Employee filename : [" + employeeFileName + "]");
        System.out.println("Tax filename : [" + taxFileName + "]");
        System.out.println("Payslip filename : [" + payslipFileName + "]");
    }

    @Command(name = "writePayslip", abbrev = "w", description = "display the employee file name, tax, and payslip that you have entered")
    public void writePayslips() throws Exception {

        AbstractPayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilderImpl();
        payslipServiceBuilder.withEmployeeFileName(employeeFileName);
        payslipServiceBuilder.withPayslipFileName(payslipFileName);
        payslipServiceBuilder.withTaxFileName(taxFileName);

        PayslipService payslipService = payslipServiceBuilder.build();
        payslipService.writePayslips(new EmployeeSpecification());
        payslipService.close();

    }
}
