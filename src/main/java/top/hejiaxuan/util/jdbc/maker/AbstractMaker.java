package top.hejiaxuan.util.jdbc.maker;

import top.hejiaxuan.util.jdbc.EntityMapperFactory;
import top.hejiaxuan.util.jdbc.EntityTableRowMapper;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 默认实现
 */
public abstract class AbstractMaker implements Maker {

    /**
     * 是否使用sql模式,默认为原生sql
     * 设置为false 的情况下, 使用 Entity 字段名称作为查询条件.
     */
    protected boolean sqlMode = true;

    /**
     * sql 占位符中的值
     */
    protected List<Object> sqlValues = new ArrayList<>();

    /**
     * entity 的 class
     */
    protected Class<?> entityClass;

    /**
     * entity 对应 table 名称
     */
    protected String tableName;

    /**
     * 完整的sql
     */
    protected StringBuffer sql = new StringBuffer();

    /**
     * sql 中的where
     */
    protected String sqlWhere = StringUtils.BLANK;

    /**
     * 实体类与数据库的映射
     */
    protected EntityTableRowMapper entityTableRowMapper;

    public AbstractMaker() {
    }

    /**
     * 设置目标
     *
     * @param entity
     * @return
     */
    @Override
    public Maker target(Class entity) {
        Assert.notNull(entity);
        EntityTableRowMapper entityTableRowMapper = EntityMapperFactory.getMapper(entity);
        this.entityTableRowMapper = entityTableRowMapper;
        this.tableName = entityTableRowMapper.getTableName();
        this.entityClass = entity;
        return this;
    }

    @Override
    public Class<?> getEntity() {
        return entityClass;
    }

    @Override
    public Object[] getSqlValues() {
        return sqlValues.toArray();
    }

    @Override
    public EntityTableRowMapper getEntityTableRowMapper() {
        return entityTableRowMapper;
    }

    /**
     * 检测查询元素是是否存在于数据库表中
     *
     * @param columnName
     * @return
     */
    @Override
    public boolean checkColumn(final String columnName) {
        if (this.entityClass == null) {
            return true;
        }
        List columnNames = entityTableRowMapper.getColumnNames();
        if (columnNames.indexOf(getColumnName(columnName)) != -1) {
            return true;
        }
        throw new UnsupportedOperationException("字段: >" + columnName + "< 不存在于 >" + tableName + "< 表中.");
    }

    /**
     * 获取数据库的字段名称.
     * 如果在非sql模式下, 根据传入的name取得相应的数据库中的字段
     *
     * @param name
     * @return
     */
    final protected String getColumnName(final String name) {
        if (sqlMode) {
            return name;
        }
        Map<String, String> fieldNameColumnMapper = entityTableRowMapper.getFieldNameColumnMapper();
        return fieldNameColumnMapper.get(name);
    }

    @Override
    public boolean isSqlMode() {
        return sqlMode;
    }
}
