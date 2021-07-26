package org.tiny.autounit.sdk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 单元测试走查注解，可以放到类上或者方法上
 * 如果放在类上，则此类下的所有公共方法将都会为其创建单元测试用例
 * 如果放在方法上，则只对此方法创建单元测试用例
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UnitWalk {
}
