package org.tiny.autounit.core.strategy;

import javassist.CtMethod;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitStrategyContent;

/**
 * @author shichaoyang
 * @Description: 处理方法体部分
 * @date 2021-07-30 17:31
 */
public class UnitMethodHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod) {
        for (CtMethod ctMethod : unitClassMethod.getCtMethods()) {

        }
        return null;
    }
}
