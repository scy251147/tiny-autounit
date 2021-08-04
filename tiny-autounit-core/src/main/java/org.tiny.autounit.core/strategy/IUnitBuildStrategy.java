package org.tiny.autounit.core.strategy;

import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitStrategyContent;

/**
 * @author shichaoyang
 * @Description: 创建单元测试用例策略
 * @date 2021-07-30 17:29
 */
public interface IUnitBuildStrategy {

    UnitStrategyContent build(UnitClassMethod unitClassMethod);

}
