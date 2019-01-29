package com.hebaibai.jdbcplus.maker.update;

import com.hebaibai.jdbcplus.maker.SqlMaker;

/**
 * 更新数据
 * @author hjx
 */
public interface Update extends SqlMaker {

    /**
     * set
     *
     * @param entity
     * @param ignoreNull 是否忽略null值
     * @return
     */
    Update set(final Object entity, boolean ignoreNull);

}
