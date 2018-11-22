package com.github.hjx601496320.simpledao.maker.insert;

import com.github.hjx601496320.simpledao.maker.SqlMaker;

/**
 * 插入数据
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
