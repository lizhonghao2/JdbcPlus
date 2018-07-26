package top.hejiaxuan.util.maker.create;

import top.hejiaxuan.util.maker.Maker;

/**
 * 创建生成数据库的sql
 */
public interface Create extends Maker {

    void setColumnTypeByField(ColumnTypeByField columnTypeByField);


}
