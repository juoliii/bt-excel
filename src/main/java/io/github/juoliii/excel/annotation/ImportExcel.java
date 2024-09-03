package io.github.juoliii.excel.annotation;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportExcel {
    int index() default -1;

    String title() default "";

    boolean notNull() default false;

    String[] dateFormat() default {};

    boolean throwException() default true;

    String defaultValueIfNull() default "";
}
