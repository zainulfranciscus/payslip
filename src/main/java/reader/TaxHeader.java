package reader;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */

public enum TaxHeader implements RowHeader{

    MIN_INCOME("Min Income"),
    MAX_INCOME("Max Income"),
    BASE_TAX("Base Tax"),
    TAX_PER_DOLLAR("Tax per Dollar");

    private String label;

    private TaxHeader(String label){
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
