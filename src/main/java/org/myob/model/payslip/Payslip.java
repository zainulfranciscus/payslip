package org.myob.model.payslip;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class Payslip {

    private final String name;
    private final String payPeriod;
    private final int grossIncome;
    private final int incomeTax;
    private final int netIncome;
    private final int aSuper;

    public Payslip(String name,
                   String payPeriod,
                   int grossIncome,
                   int incomeTax,
                   int netIncome,
                   int aSuper){
        this.name = name;
        this.payPeriod = payPeriod;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.aSuper = aSuper;
    }

    public String getEmployeeName(){
        return name;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public int getSuper() {
        return aSuper;
    }
}
