package org.tiny.autounit.core.model;

import javassist.CtMethod;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author shichaoyang
 * @Description: 消息体配对 javassit的ctmethod和java反射的method
 * @Note 有时候一些功能单靠静态字节码不好分析，需要结合着反射使用
 * @date 2021-08-04 17:57
 */
@Data
public class UnitMethodPair {

    private CtMethod ctMethod;

    private Method method;

}
