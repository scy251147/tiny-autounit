package org.tiny.autounit.core.model;

import javassist.CtMethod;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author shichaoyang
 * @Description: 消息体配对 javassit的ctmethod和java反射的method
 * @date 2021-08-04 17:57
 */
@Data
public class UnitMethodPair {

    private CtMethod ctMethod;

    private Method method;

}
