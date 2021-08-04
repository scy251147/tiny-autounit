package org.tiny.autounit.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.utils.FileOpsUtil;
import org.tiny.autounit.core.utils.RegexUtil;

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

        //获取类模板
        String classTemplateContent = FileOpsUtil.readTemplate("template/ClassTemplate");

        //替换掉packageName
        classTemplateContent = classTemplateContent.replace("$${template-package-name}$$", packageName);

        //替换类名
        classTemplateContent = classTemplateContent.replace("$${test-class-name}$$", RegexUtil.getFormatedClassName(unitClassMethod.getCtClass().getName()));

        //组装field并替换
        UnitStrategyContent fieldContent = makeInjectField(unitClassMethod);
        classTemplateContent = classTemplateContent.replace("$${inject-field}$$", fieldContent.getContent().get(UnitClassType.inject_field));

        //替换import package
        classTemplateContent = classTemplateContent.replace("$${import-path}$$", fieldContent.getContent().get(UnitClassType.import_path));

        //组装方法并替换

        //组装入参并替换

        //最终返回结果
        return classTemplateContent;

    }

    /**
     * 构建方法入参
     */
    private static UnitStrategyContent makeMethodRequest(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitRequestHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

    /**
     * 构建注入对象
     */
    private static UnitStrategyContent makeInjectField(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitFieldHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

    /**
     * 构建方法体内容
     */
    private static UnitStrategyContent makeMethodBody(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitMethodHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

}
