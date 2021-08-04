package org.tiny.autounit.core.strategy;

import javassist.CtMethod;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.utils.FileOpsUtil;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 处理方法体部分
 * @date 2021-07-30 17:31
 */
public class UnitMethodHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod) {

        UnitStrategyContent unitStrategyContent = new UnitStrategyContent();

        //空对象则直接返回
        if (unitClassMethod == null || unitClassMethod.getCtMethods() == null || unitClassMethod.getCtMethods().size() == 0) {
            return unitStrategyContent;
        }

        //遍历集合，生成单测方法
        for (CtMethod ctMethod : unitClassMethod.getCtMethods()) {
            String methodName = "when_" + ctMethod.getName() + "_then_return_success";
            String methodBody = getMehtodTemplateContent();
            //替换掉方法
            methodBody = methodBody.replace(UnitClassType.test_method_name.getExpr(), methodName);
            //不存在则添加
            if (!unitStrategyContent.getContent().containsKey(UnitClassType.test_method_body)) {
                StringBuilder methodBodyBuilder = new StringBuilder();
                methodBodyBuilder.append(methodBody);
                unitStrategyContent.getContent().put(UnitClassType.test_method_body, methodBodyBuilder);
            }
            //存在则追加
            else {
                unitStrategyContent.getContent().get(UnitClassType.test_method_body)
                        .append(RegexUtil.newLine())
                        .append(RegexUtil.newTab())
                        .append(methodBody);
            }
        }

        return unitStrategyContent;
    }

    /**
     * 获取方法模板内容
     *
     * @return
     */
    private String getMehtodTemplateContent() {
        String methodTemplateContent = FileOpsUtil.readTemplate("template/MethodTemplate");
        return methodTemplateContent;
    }
}
