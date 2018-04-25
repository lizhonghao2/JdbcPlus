package top.hejiaxuan.util.jdbc.maker;

import top.hejiaxuan.util.jdbc.EntityTableRowMapper;

/**
 * sql生成器
 */
public interface Maker {

    /**
     * 设置目标
     * entity 的 Class 必须添加@Table注解
     *
     * @param entity
     * @return
     */
    Maker target(final Class entity);

    /**
     * 获取数据库表对应的entity.class
     *
     * @return
     */
    Class<?> getEntity();

    /**
     * 获取sql
     *
     * @return
     */
    String toSql();

    /**
     * 获取sql 中的数据
     *
     * @return
     */
    Object[] getSqlValues();

    /**
     * 获取 Entity与Table 的映射
     *
     * @return
     */
    EntityTableRowMapper getEntityTableRowMapper();

    /**
     * 检查字段是否存在与数据库表中
     *
     * @param columnName
     * @return
     */
    boolean checkColumn(final String columnName);

    /**
     * 是否是sql模式,
     * 如果不是sql 模式 使用entity 属性名作为查询条件
     * (现在已经废弃)
     *
     * @return
     */
    boolean isSqlMode();
}
