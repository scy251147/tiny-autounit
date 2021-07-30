package org.tiny.autounit.core.strategy;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;

/**
 * @author shichaoyang
 * @Description: 处理注入部分
 * @date 2021-07-30 17:30
 */
@Slf4j
public class UnitFieldHandleStrategy implements IUnitBuildStrategy {

    @Override
    public void build(UnitClassMethod unitClassMethod) {
      System.out.println(unitClassMethod.getCtClass().getName());
      unitClassMethod.getClass().getDeclaredFields();
    }
}
