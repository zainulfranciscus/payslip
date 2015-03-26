package reader;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public abstract class Row {

    private final Map<RowHeader,String> values = new HashMap<RowHeader, String>();

    public String get(RowHeader header){
        return values.get(header);
    }

    public int getInt(RowHeader header) {
        return NumberUtils.toInt(get(header));
    }

    protected void put(RowHeader header, String value){
        values.put(header,value);
    }

}
