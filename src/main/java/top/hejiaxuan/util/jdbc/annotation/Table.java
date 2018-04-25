package top.hejiaxuan.util.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明是一个table
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    
    /**
     * 标识表的名称
     *
     * @return
     */
    String value();
    
    /**
     * 表的注释
     *
     * @return
     */
    String comment() default "";
    
}
