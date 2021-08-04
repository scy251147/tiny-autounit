package org.tiny.autounit.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
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

        //1. 获取类模板
        String classTemplateContent = FileOpsUtil.readTemplate("template/ClassTemplate");

        //2. 替换掉packageName
        classTemplateContent = classTemplateContent.replace("$${template-package-name}$$", packageName);

        //3. 替换类名
        classTemplateContent = classTemplateContent.replace("$${test-class-name}$$", RegexUtil.getFormatedClassName(unitClassMethod.getCtClass().getName()));

        //4. 替换import package, 暂替为空
        classTemplateContent = classTemplateContent.replace("$${import-path}$$", "");

        //3. 组装field并替换
        String fieldContent = makeInjectField(unitClassMethod);
        classTemplateContent = classTemplateContent.replace("$${inject-field}$$", fieldContent);

        //4. 组装方法并替换

        //5. 组装入参并替换

        //最终返回结果
        return classTemplateContent;

    }

    /**
     * 构建方法入参
     */
    private static String makeMethodRequest(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitRequestHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

    /**
     * 构建注入对象
     */
    private static String makeInjectField(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitFieldHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

    /**
     * 构建方法体内容
     */
    private static String makeMethodBody(UnitClassMethod unitClassMethod) {
        IUnitBuildStrategy unitBuildStrategy = new UnitMethodHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod);
    }

}
