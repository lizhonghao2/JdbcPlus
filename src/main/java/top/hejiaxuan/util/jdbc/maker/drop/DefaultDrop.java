package top.hejiaxuan.util.jdbc.maker.drop;

import top.hejiaxuan.util.jdbc.maker.AbstractMaker;

public class DefaultDrop extends AbstractMaker implements Drop {
    
    public static final String DROP_TABLE = "DROP TABLE %s;";
    
    @Override
    public String toSql() {
        return String.format(DROP_TABLE, entityTableRowMapper.getTableName());
    }
}
