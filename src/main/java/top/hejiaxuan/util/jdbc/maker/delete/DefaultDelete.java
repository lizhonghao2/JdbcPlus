package top.hejiaxuan.util.jdbc.maker.delete;

import top.hejiaxuan.util.jdbc.maker.condition.SqlWhere;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

/**
 * 默认的删除
 */
public class DefaultDelete extends SqlWhere implements Delete {

    @Override
    public String toSql() {
        Assert.isTrue(sqlWhere.length() != 0, "没有添加条件");
        sql.append(StringUtils.append("DELETE FROM ", tableName, " WHERE ", sqlWhere));
        String sqlStr = sql.toString();
        return sqlStr;
    }

    @Override
    public Object[] getSqlValues() {
        sqlValues.addAll(super.whereValues);
        return sqlValues.toArray();
    }

}
