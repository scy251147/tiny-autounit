package org.tiny.autounit.core.strategy;

/**
 * @author shichaoyang
 * @Description: 构建上下文
 * @date 2021-07-30 17:32
 */
public class UnitBuildContext {

    /**
     * 带参构造
     *
     * @param unitBuildStrategy
     */
    public UnitBuildContext(IUnitBuildStrategy unitBuildStrategy) {
        this.unitBuildStrategy = unitBuildStrategy;
    }

    //构建策略
    private IUnitBuildStrategy unitBuildStrategy;

    /**
     * 构建具体部件
     */
    public void build() {
        unitBuildStrategy.build();
    }

}
