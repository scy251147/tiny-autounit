package org.tiny.autounit.core.strategy;

import javassist.bytecode.LocalVariableAttribute;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.parser.ParseAssertionTemplate;
import org.tiny.autounit.core.parser.ParseInputParamsTemplate;
import org.tiny.autounit.core.parser.ParseOutputParamTemplate;
import org.tiny.autounit.core.utils.FileOpsUtil;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 处理方法体部分
 * @date 2021-07-30 17:31
 */
@Slf4j
public class UnitMethodHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {

        UnitStrategyContent unitStrategyContent = new UnitStrategyContent();

        //空对象则直接返回
        if (unitClassMethod == null || unitClassMethod.getMethodPairs() == null || unitClassMethod.getMethodPairs().size() == 0) {
            return unitStrategyContent;
        }

        //遍历集合，生成单测方法
        for (UnitMethodPair methodPair : unitClassMethod.getMethodPairs()) {
            String methodName = "when_" + methodPair.getCtMethod().getName() + "_then_return_success";
            String methodBody = getMehtodTemplateContent();
            //替换掉方法名称
            methodBody = methodBody.replace(UnitClassType.test_method_name.getExpr(), methodName);
            //替换掉方法内容
            methodBody = methodBody.replace(UnitClassType.test_method_body.getExpr(), handleMethodTemplateContent(methodPair, unitMockContext));
            //不存在则添加
            if (!unitStrategyContent.getContent().containsKey(UnitClassType.method_body)) {
                unitStrategyContent.getContent().put(UnitClassType.method_body, new StringBuilder().append(methodBody));
            }
            //存在则追加
            else {
                unitStrategyContent.getContent().get(UnitClassType.method_body)
                        .append(RegexUtil.newLine())
                        .append(RegexUtil.new4Tab())
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

    /**
     * 处理方法体
     *
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    private String handleMethodTemplateContent(UnitMethodPair methodPair, UnitMockContext unitMockContext) {

        StringBuilder builder = new StringBuilder();

        //返参处理
        builder.append(new ParseOutputParamTemplate().parse(methodPair, unitMockContext));

        //入参处理
        builder.append((new ParseInputParamsTemplate()).parse(methodPair, unitMockContext));

        //断言处理
        builder.append(new ParseAssertionTemplate().parse(methodPair, unitMockContext));

        //结果返回
        return builder.toString();
    }
}