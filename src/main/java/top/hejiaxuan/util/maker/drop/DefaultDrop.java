package top.hejiaxuan.util.maker.drop;

import top.hejiaxuan.util.maker.AbstractMaker;
import top.hejiaxuan.util.maker.And;
import top.hejiaxuan.util.maker.SqlMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除表格
 *
 * @author hjx
 */
public class DefaultDrop extends AbstractMaker implements Drop {

    public static final String DROP_TABLE = "DROP TABLE %s;";

    @Override
    public String makeSql() {
        return String.format(DROP_TABLE, entityTableRowMapper.getTableName());
    }

    @Override
    public List<Object> makeSqlValue() {
        return new ArrayList<>();
    }

    @Override
    public SqlMaker where(And... ands) {
        throw new UnsupportedOperationException("不支持的操作");
    }
}
