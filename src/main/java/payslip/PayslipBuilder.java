package payslip;

import builder.Builder;

/**
 * Created by Zainul Franciscus on 25/03/2015.
 */
public class PayslipBuilder implements Builder<Payslip> {

    private MONTH month;
    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int aSuper;

    public PayslipBuilder withMonth(MONTH month) {
        this.month = month;
        return this;
    }

    public Payslip build() {
        return new Payslip(month,grossIncome,incomeTax,netIncome,aSuper);
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
