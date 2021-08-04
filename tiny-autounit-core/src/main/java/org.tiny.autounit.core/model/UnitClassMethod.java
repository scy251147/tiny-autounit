package org.tiny.autounit.core.model;

import javassist.CtClass;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shichaoyang
 * @Description: 类和方法集合
 * @date 2021-07-29 21:35
 */
@Data
public class UnitClassMethod {

    /**
     * 测试类CtClass类型对象
     */
    private CtClass ctClass;

    /**
     * 测试类Class类型对象
     */
    private Class clazz;

    /**
     * 测试方法
     */
    private List<UnitMethodPair> methodPairs = new ArrayList<>();

}
