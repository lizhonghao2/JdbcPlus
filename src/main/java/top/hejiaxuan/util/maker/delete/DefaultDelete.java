package top.hejiaxuan.util.maker.delete;

import org.springframework.util.Assert;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import top.hejiaxuan.util.maker.condition.SqlWhere;

/**
 * 默认的删除
 */
public class DefaultDelete extends SqlWhere implements Delete {

    @Override
    public String toSql() {
        Assert.isTrue(sqlWhere.length() != 0, "没有添加条件");
        if (sqlComplete) {
            return sql.toString();
        }
        sqlComplete = true;
        sql.append(StringUtils.append("DELETE FROM ", tableName, " WHERE ", sqlWhere));
        return sql.toString();
    }

    @Override
    public Object[] getSqlValues() {
        if (sqlValueComplete) {
            return sqlValues.toArray();
        }
        sqlValueComplete = true;
        sqlValues.addAll(super.whereValues);
        return sqlValues.toArray();
    }

}
