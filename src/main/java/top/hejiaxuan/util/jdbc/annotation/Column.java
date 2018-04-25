package top.hejiaxuan.util.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示一个字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    
    /**
     * 字段在数据库中的名称
     *
     * @return
     */
    String value();
    
    /**
     * 数据库中的注释
     *
     * @return
     */
    String comment() default "";
    
}
