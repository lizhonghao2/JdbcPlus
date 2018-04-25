package top.hejiaxuan.util.jdbc.maker.insert;

import top.hejiaxuan.util.jdbc.maker.Maker;

/**
 * 插入数据
 */
public interface Insert extends Maker {

    /**
     * 插入数据
     *
     * @param value
     * @return
     */
    boolean insert(final Object value);

}
