package top.hejiaxuan.util.maker.update;

import top.hejiaxuan.util.maker.SqlMaker;

/**
 * 更新数据
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
