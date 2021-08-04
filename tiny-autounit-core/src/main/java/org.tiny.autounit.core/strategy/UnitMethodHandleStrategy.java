package org.tiny.autounit.core.strategy;

import javassist.CtMethod;
import javassist.bytecode.LocalVariableAttribute;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.model.context.UnitMockContext;
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
        if (unitClassMethod == null || unitClassMethod.getCtMethods() == null || unitClassMethod.getCtMethods().size() == 0) {
            return unitStrategyContent;
        }

        //遍历集合，生成单测方法
        for (CtMethod ctMethod : unitClassMethod.getCtMethods()) {
            String methodName = "when_" + ctMethod.getName() + "_then_return_success";
            String methodBody = getMehtodTemplateContent();
            //替换掉方法名称
            methodBody = methodBody.replace(UnitClassType.test_method_name.getExpr(), methodName);
            //替换掉方法内容
            methodBody = methodBody.replace(UnitClassType.test_method_body.getExpr(), handleMethodTemplateContent(ctMethod, unitMockContext));
            //不存在则添加
            if (!unitStrategyContent.getContent().containsKey(UnitClassType.method_body)) {
                unitStrategyContent.getContent().put(UnitClassType.method_body, new StringBuilder().append(methodBody));
            }
            //存在则追加
            else {
                unitStrategyContent.getContent().get(UnitClassType.method_body)
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

    /**
     * 处理方法体
     *
     * @param currentMethod
     * @return
     */
    private String handleMethodTemplateContent(CtMethod currentMethod, UnitMockContext unitMockContext) {

        try {
            ExprEditor exprEditor = new ExprEditor() {
                public void edit(MethodCall m) {
                    try {
                        String methodCallName = m.getClassName();
                        Class refClass = Class.forName(methodCallName);
                        System.out.println("-->" + refClass.getName() + "." + m.getMethodName());
                    } catch (Exception e) {
                        log.error("UnitMethodHandleStrategy.handleMethodTemplateContent.edit error. ", e);
                    }
                }
            };
            currentMethod.instrument(exprEditor);
        } catch (Exception e) {
            log.error("UnitMethodHandleStrategy.handleMethodTemplateContent error ", e);
        }

        StringBuilder builder = new StringBuilder();

        LocalVariableAttribute nameTable = (LocalVariableAttribute) currentMethod.getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        int variableLength = nameTable.tableLength();
        //有参
        if (variableLength > 0) {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + currentMethod.getName() + "(");
            for (int i = 1; i < variableLength; i++) {
                int frameWithNameAtConstantPool = nameTable.nameIndex(i);
                String variableName = currentMethod.getMethodInfo().getConstPool().getUtf8Info(frameWithNameAtConstantPool);
                builder.append(variableName);
                if (i < variableLength - 1) {
                    builder.append(",");
                }
            }
            builder.append(");");
        }
        //无参
        else {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + currentMethod.getName() + "();");
        }

        builder.append(RegexUtil.newLine()).append(RegexUtil.newTab()).append(RegexUtil.newTab());
        builder.append("//todo user defined assert");

        return builder.toString();
    }
}