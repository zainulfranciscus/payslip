package org.accounting.model.payslip;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipBuilder {

    private String name;
    private String payPeriod;
    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int aSuper;

    public PayslipBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PayslipBuilder withPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
        return this;
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

    public Payslip build() {
        return new Payslip(name, payPeriod, grossIncome, incomeTax, netIncome, aSuper);
    }
}
