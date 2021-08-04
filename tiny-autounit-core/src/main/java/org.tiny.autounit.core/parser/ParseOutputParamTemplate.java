package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 出参解析
 * @date 2021-08-04 18:17
 */
public class ParseOutputParamTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {

        StringBuilder stringBuilder = new StringBuilder();

        //获取返回值
        String returnType = RegexUtil.getClassName(methodPair.getMethod().getReturnType().getName());
        if (!returnType.equals("void")) {
            stringBuilder.append(returnType + " returnResult = ");
        }

        //将返回值类型暂存上下文
        unitMockContext.setReturnType(returnType);

        return stringBuilder.toString();
    }
}
