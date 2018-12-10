package com.github.hjx601496320.jdbcplus.maker.insert;

import com.github.hjx601496320.jdbcplus.maker.SqlMaker;

/**
 * 插入数据
 * @author hjx
 */
public interface Insert extends SqlMaker {

    /**
     * 插入数据
     *
     * @param value
     * @return
     */
    boolean insert(final Object value);

}
