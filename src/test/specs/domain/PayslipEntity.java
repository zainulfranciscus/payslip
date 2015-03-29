package domain;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
public class PayslipEntity {

    private final String name;
    private final String payPeriod;
    private final String grossIncome;
    private final String incomeTax;
    private final String netIncome;
    private final String aSuper;

    public PayslipEntity(String name,String payPeriod, String grossIncome, String incomeTax, String netIncome, String aSuper){
        this.name = name;
        this.payPeriod = payPeriod;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.aSuper = aSuper;
    }

    public String getName(){
        return name;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public String getGrossIncome() {
        return grossIncome;
    }

    public String getIncomeTax() {
        return incomeTax;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public String getSuper() {
        return aSuper;
    }
}
