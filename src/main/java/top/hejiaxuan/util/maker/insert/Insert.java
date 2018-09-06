package top.hejiaxuan.util.maker.insert;

import top.hejiaxuan.util.maker.SqlMaker;

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
