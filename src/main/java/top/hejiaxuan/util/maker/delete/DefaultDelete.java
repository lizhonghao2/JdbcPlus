package top.hejiaxuan.util.maker.delete;

import top.hejiaxuan.util.maker.condition.SqlWhere;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

/**
 * 默认的删除
 */
public class DefaultDelete extends SqlWhere implements Delete {

    @Override
    public String toSql() {
        Assert.isTrue(sqlWhere.length() != 0, "没有添加条件");
        if (completeSql != null) {
            return completeSql;
        }
        sql.append(StringUtils.append("DELETE FROM ", tableName, " WHERE ", sqlWhere));
        completeSql = sql.toString();
        return completeSql;
    }

    @Override
    public Object[] getSqlValues() {
        if (completeSqlValues != null) {
            return completeSqlValues;
        }
        sqlValues.addAll(super.whereValues);
        completeSqlValues = sqlValues.toArray();
        return completeSqlValues;
    }

}
