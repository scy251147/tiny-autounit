package org.tiny.autounit.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;

/**
 * @author shichaoyang
 * @Description: 构建
 * @date 2021-07-30 17:38
 */
@Slf4j
public class UnitBuildFactory {

    /**
     * 构建测试类内容, 将解析完毕的内容组装成一个测试类
     *
     * @return
     */
    public static String makeContent(UnitClassMethod unitClassMethod, String packageName) {
        makeMethodRequest(unitClassMethod);
        makeInjectField(unitClassMethod);
        makeMethodBody(unitClassMethod);
        return null;
    }

    /**
     * 构建方法入参
     */
    private static void makeMethodRequest(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitRequestHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build(unitClassMethod);
    }

    /**
     * 构建注入对象
     */
    private static void makeInjectField(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitFieldHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build(unitClassMethod);
    }

    /**
     * 构建方法体内容
     */
    private static void makeMethodBody(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitMethodHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        context.build(unitClassMethod);
    }

}
