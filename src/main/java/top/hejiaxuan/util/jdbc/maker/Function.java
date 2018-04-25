package top.hejiaxuan.util.jdbc.maker;

import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

import java.util.Random;

/**
 * sql 函数封装
 *
 * @param <T> 函数的返回值
 */
public class Function<T> {

    private String sql;

    private String resultName;

    private Class resultType;

    /**
     * 统计数量
     *
     * @param columnName
     * @return
     */
    public static Function<Long> count(String columnName) {
        Assert.notNull(columnName, "columnName 不能为空");
        String key = getRandom("count");
        String sql = StringUtils.append("count(", columnName, ") as ", key);
        return new Function(sql, key, Long.class);
    }

    /**
     * 求和
     *
     * @param columnName
     * @return
     */
    public static Function<Double> sum(String columnName) {
        Assert.notNull(columnName, "columnName 不能为空");
        String key = getRandom("sum");
        String sql = StringUtils.append("sum(", columnName, ") as ", key);
        return new Function(sql, key, Double.class);
    }

    private Function(String sql, String resultName, Class resultType) {
        this.resultType = resultType;
        this.resultName = resultName;
        this.sql = sql;
    }

    /**
     * 给别名添加随机数,避免重名
     *
     * @param key
     * @return
     */
    private static String getRandom(String key) {
        Random random = new Random();
        int abs = Math.abs(random.nextInt());
        String str = StringUtils.append(key, "_", abs);
        return str;
    }

    public String getResultName() {
        return resultName;
    }

    public String getSql() {
        return sql + StringUtils.SPACE;
    }

    public Class getResultType() {
        return resultType;
    }
}
