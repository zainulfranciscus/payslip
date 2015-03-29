package domain;

/**
 * Created by Lenovo on 29/03/2015.
 */
public class PayslipEntityBuilder {

    private String name;
    private String payPeriod;
    private String grossIncome;
    private String incomeTax;
    private String netIncome;
    private String aSuper;

    public PayslipEntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PayslipEntityBuilder withPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
        return this;
    }

    public PayslipEntityBuilder withGrossIncome(String grossIncome) {
        this.grossIncome = grossIncome;
        return this;
    }

    public PayslipEntityBuilder withIncomeTax(String incomeTax) {
        this.incomeTax = incomeTax;
        return this;
    }

    public PayslipEntityBuilder withNetIncome(String netIncome) {
        this.netIncome = netIncome;
        return this;
    }

    public PayslipEntityBuilder withSuper(String aSuper) {
        this.aSuper = aSuper;
        return this;
    }

    public PayslipEntity build() {
        return new PayslipEntity(name, payPeriod, grossIncome, incomeTax, netIncome, aSuper);
    }
}
