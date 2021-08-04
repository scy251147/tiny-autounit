package org.tiny.autounit.core.strategy;

import javassist.CtField;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 处理注入部分
 * @date 2021-07-30 17:30
 */
@Slf4j
public class UnitFieldHandleStrategy implements IUnitBuildStrategy {

    @Override
    public String build(UnitClassMethod unitClassMethod) {

        CtField[] declaredFields = unitClassMethod.getCtClass().getDeclaredFields();

        String classFullName = unitClassMethod.getCtClass().getName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@InjectMocks").append("\n");
        stringBuilder.append("    private "+ RegexUtil.getClassName(classFullName)+ " " + RegexUtil.getClassVariableName(classFullName)).append("\n");
        stringBuilder.append("\n");

        for (CtField declaredField : declaredFields) {
            stringBuilder.append("    @Mock").append("\n");
            stringBuilder.append("    private " + RegexUtil.getClassName(declaredField.getFieldInfo().getDescriptor().replace(";",""), "/") + " " + declaredField.getFieldInfo().getName());
        }

        return stringBuilder.toString();
    }
}
