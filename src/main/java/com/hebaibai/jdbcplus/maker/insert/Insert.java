package com.hebaibai.jdbcplus.maker.insert;

import com.hebaibai.jdbcplus.maker.SqlMaker;

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
