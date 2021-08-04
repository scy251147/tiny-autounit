package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;

/**
 * @author shichaoyang
 * @Description: 模板解析策略
 * @date 2021-08-04 9:38
 */
public interface IMethodBodyParse {

    String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext);

}
