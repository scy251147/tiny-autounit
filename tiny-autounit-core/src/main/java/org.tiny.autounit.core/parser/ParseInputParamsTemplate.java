package org.tiny.autounit.core.parser;

import javassist.bytecode.LocalVariableAttribute;
import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;

/**
 * @author shichaoyang
 * @Description: 入参解析
 * @date 2021-08-04 18:28
 */
public class ParseInputParamsTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {

        StringBuilder builder = new StringBuilder();

        LocalVariableAttribute nameTable = (LocalVariableAttribute) methodPair.getCtMethod().getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        int variableLength = nameTable.tableLength();
        //有参
        if (variableLength > 0) {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + methodPair.getCtMethod().getName() + "(");
            for (int i = 1; i < variableLength; i++) {
                int frameWithNameAtConstantPool = nameTable.nameIndex(i);
                String variableName = methodPair.getCtMethod().getMethodInfo().getConstPool().getUtf8Info(frameWithNameAtConstantPool);
                builder.append(variableName);
                if (i < variableLength - 1) {
                    builder.append(",");
                }
            }
            builder.append(");");
        }
        //无参
        else {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + methodPair.getCtMethod().getName() + "();");
        }

        return  builder.toString();
    }
}
