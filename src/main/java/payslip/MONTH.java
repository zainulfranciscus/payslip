package payslip;

import java.math.BigDecimal;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
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

    public static BigDecimal numberOfMonthsAsBigDecimal(){
        return new BigDecimal(MONTH.values().length);
    }
}
