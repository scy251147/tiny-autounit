package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 断言处理
 * @date 2021-08-04 18:32
 */
public class ParseAssertionTemplate implements IMethodBodyParse{

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        if(unitMockContext == null || unitMockContext.getReturnType() == null){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        //非void类型添加assert断言
        if (!unitMockContext.getReturnType().equals("void")) {
            builder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
            builder.append("assert returnResult != null;");
        }

        return builder.toString();
    }
}
