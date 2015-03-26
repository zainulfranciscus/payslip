package reader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public enum EmployeeHeader implements RowHeader {

    FIRST_NAME("first name"),
    LAST_NAME("last name"),
    ANNUAL_SALARY("annual salary"),
    SUPER_RATE("super rate"),
    PAYMENT_DATE("payment start date");

    private String label;

    private EmployeeHeader(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
