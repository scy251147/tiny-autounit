package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 方法体解析
 * @date 2021-08-04 18:16
 */
public class ParseMethodBodyTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        builder.append("//todo 构造数据并打桩");
        builder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab());
        return builder.toString();
    }
}
