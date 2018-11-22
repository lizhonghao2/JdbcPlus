package com.github.hjx601496320.simpledao.maker.query;

import com.github.hjx601496320.simpledao.maker.SqlMaker;

/**
 * 查询
 */
public interface Query extends SqlMaker {

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
     * @param orderBy
     * @param type:   ASC/DESC
     * @return
     */
    Query orderBy(String orderBy, String type);

    /**
     * limit
     *
     * @param line
     * @param num
     * @return
     */
    Query limit(int line, int num);

}
