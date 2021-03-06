package org.accounting.persistence.row;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.accounting.persistence.mapping.RowHeader;
import org.accounting.persistence.row.specification.RowSpecification;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class Row {

    protected String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";
    protected String MONTH_FORMAT_MMM = "MMM";

    private final Map<RowHeader, String> values = new HashMap<>();

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

    public boolean matchesSpecification(RowSpecification specification){
        return specification.isValid(this);
    }

    protected LocalDate toLocalDate(String day, String month, String year) {
        LocalDate date = null;

        try {
            date = DateTimeFormat.forPattern(DATE_FORMAT_DD_MMM_YYYY).parseLocalDate(day + " " + StringUtils.upperCase(month) + " " + year);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }

}
