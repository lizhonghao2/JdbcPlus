package top.hejiaxuan.util.jdbc.maker.query;

import top.hejiaxuan.util.jdbc.maker.condition.Where;

/**
 * 查询
 */
public interface Query extends Where {

    /**
     * 添加查询内容
     *
     * @param columnName
     * @return
     */
    boolean addSelection(String... columnName);

    /**
     * 添加查询内容
     *
     * @param check      是否检查字段是否存在
     * @param columnName
     * @return
     */
    boolean addSelection(boolean check, String... columnName);

    /**
     * 排序
     *
     * @param orderColumn
     * @param type:       ASC/DESC
     * @return
     */
    Query orderBy(String[] orderColumn, String type);

    /**
     * limit
     *
     * @param line
     * @param num
     * @return
     */
    Query limit(int line, int num);

}
