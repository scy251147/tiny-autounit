package org.tiny.autounit.sdk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 单元测试包扫描器，value值为包路径，此注解加到main方法上，即可实现批量单测生成
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface UnitScan {

  String value() default "";

}
