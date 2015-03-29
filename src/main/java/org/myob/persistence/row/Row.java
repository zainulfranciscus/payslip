package org.myob.persistence.row;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.myob.persistence.mapping.RowHeader;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class Row {

    protected String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";
    protected String MONTH_FORMAT_MMM = "MMM";

    private final Map<RowHeader, String> values = new HashMap<RowHeader, String>();

    public String get(RowHeader header) {
        return values.get(header);
    }

    public int getInt(RowHeader header) {
        return NumberUtils.toInt(get(header));
    }

    public double getDouble(RowHeader header) {
        return NumberUtils.toDouble(get(header));
    }

    protected void put(RowHeader header, String value) {
        values.put(header, value);
    }

    public int getMonthAsInt(RowHeader month) {
        String monthName = get(month);

        DateTimeFormatter format = DateTimeFormat.forPattern(MONTH_FORMAT_MMM);
        DateTime instance = format.withLocale(Locale.ENGLISH).parseDateTime(monthName);

        return instance.getMonthOfYear();
    }

    public DateTimeFormatter format(){
        return DateTimeFormat.forPattern(DATE_FORMAT_DD_MMM_YYYY);
    }


}
