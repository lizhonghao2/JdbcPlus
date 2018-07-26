package top.hejiaxuan.util.maker.insert;

import top.hejiaxuan.util.maker.Maker;

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
