package org.myob.payslip;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class Payslip {

    private final int grossIncome;
    private final int incomeTax;
    private final int netIncome;
    private final int aSuper;


    public Payslip(int grossIncome, int incomeTax, int netIncome, int aSuper) {

        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.aSuper = aSuper;
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
