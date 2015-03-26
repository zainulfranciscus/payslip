package org.myob.payslip;

import org.myob.builder.Builder;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipBuilder implements Builder<Payslip> {

    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int aSuper;

    public Payslip build() {
        return new Payslip(grossIncome,incomeTax,netIncome,aSuper);
    }

    public PayslipBuilder withGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
        return this;
    }

    public PayslipBuilder withIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
        return this;
    }

    public PayslipBuilder withNetIncome(int netIncome) {
        this.netIncome = netIncome;
        return this;
    }

    public PayslipBuilder withSuper(int aSuper) {
        this.aSuper = aSuper;
        return this;
    }
}
