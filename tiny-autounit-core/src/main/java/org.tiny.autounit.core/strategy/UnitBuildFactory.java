package org.tiny.autounit.core.strategy;

import org.tiny.autounit.core.model.UnitClassMethod;

/**
 * @author shichaoyang
 * @Description: 构建
 * @date 2021-07-30 17:38
 */
public class UnitBuildFactory {

    /**
     * 构建测试类内容
     *
     * @return
     */
    public static String makeContent(UnitClassMethod unitClassMethod) {
        makeMethodRequest();
        makeInjectField();
        makeMethodBody();
        return null;
    }

    /**
     * 构建方法入参
     */
    private static void makeMethodRequest() {
        IUnitBuildStrategy unitBuildStrategy = new UnitRequestHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build();
    }

    /**
     * 构建注入对象
     */
    private static void makeInjectField() {
        IUnitBuildStrategy unitBuildStrategy = new UnitFieldHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build();
    }

    /**
     * 构建方法体内容
     */
    private static void makeMethodBody() {
        IUnitBuildStrategy unitBuildStrategy = new UnitMethodHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build();
    }

}
