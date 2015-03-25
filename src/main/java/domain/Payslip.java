package domain;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Lenovo on 25/03/2015.
 */
public class Payslip {


    public enum MONTH {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER;
    }

    private MONTH month;
    private int grossIncome;
    private final int incomeTax;
    private int netIncome;
    private int aSuper;

    public Payslip(MONTH month, int grossIncome, int incomeTax, int netIncome, int aSuper) {
        this.month = month;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.aSuper = aSuper;
    }

    public MONTH getMonth() {
        return month;
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

    public int getSuper(){
        return aSuper;
    }

    public static BigDecimal numberOfMonthsAsBigDecimal(){
        return new BigDecimal(MONTH.values().length);
    }

}
