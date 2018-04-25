package top.hejiaxuan.util.jdbc.maker.condition;

import top.hejiaxuan.util.jdbc.maker.Maker;

import java.util.List;

/**
 * 查询条件
 */
public interface Where extends Maker {

    /**
     * 添加条件
     *
     * @param and
     * @return
     */
    Where where(And... and);

    /**
     * 将一个新的Where对象 替换掉自己的条件
     *
     * @param where
     * @return
     */
    boolean newWhere(Where where);

    And in(String columnName, Object[] values);

    And equal(String columnName, Object value);

    And notEqual(String columnName, Object value);

    /**
     * 大于
     *
     * @param columnName
     * @param value
     * @param andEquals  是否 等于
     * @return
     */
    And greater(String columnName, Object value, boolean andEquals);

    /**
     * 小于
     *
     * @param columnName
     * @param value
     * @param andEquals  是否等于
     * @return
     */
    And less(String columnName, Object value, boolean andEquals);

    And like(String columnName, Object value);

    /**
     * 取出条件
     *
     * @return
     */
    List<And> getWhere();
}
