package reader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public interface Row {

    static final String MIN_INCOME = "Min Income";
    static final String MAX_INCOME = "Max Income";
    static final String BASE_TAX = "Base Tax";
    static final String TAX_PER_DOLLAR = "Tax per Dollar";

    int getInt(String columnName);
}
