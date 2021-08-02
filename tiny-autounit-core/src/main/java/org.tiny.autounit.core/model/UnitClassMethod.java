package org.tiny.autounit.core.model;

import javassist.CtClass;
import javassist.CtMethod;
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
     * 测试类
     */
    private CtClass ctClass;

    /**
     * 测试方法
     */
    private List<CtMethod> ctMethods = new ArrayList<>();

}
