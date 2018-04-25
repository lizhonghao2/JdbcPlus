package top.hejiaxuan.util.jdbc.maker.create;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ColumnTypeByFieldImpl implements ColumnTypeByField {

    private Map<Class, String> mapping = new HashMap<>();

    private String STRING = "VARCHAR(225)";
    private String INT = "INTEGER";
    private String DATE = "DATETIME";
    private String LONG = "BIGINT";
    private String FLOAT = "FLOAT";
    private String DOUBLE = "DOUBLE";

    {
        mapping.put(int.class, INT);
        mapping.put(Integer.class, INT);
        mapping.put(long.class, LONG);
        mapping.put(Long.class, LONG);
        mapping.put(float.class, FLOAT);
        mapping.put(Float.class, FLOAT);
        mapping.put(double.class, DOUBLE);
        mapping.put(Double.class, DOUBLE);

        mapping.put(String.class, STRING);
        mapping.put(Date.class, DATE);
    }

    @Override
    public String getColumnType(Class fieldType) {
        return mapping.get(fieldType);
    }

}
