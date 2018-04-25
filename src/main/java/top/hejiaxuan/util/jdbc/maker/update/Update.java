package top.hejiaxuan.util.jdbc.maker.update;

import top.hejiaxuan.util.jdbc.maker.condition.Where;

/**
 * 更新数据
 */
public interface Update extends Where {

    /**
     * set
     *
     * @param entity
     * @param selective
     * @return
     */
    boolean set(final Object entity, boolean selective);
}
