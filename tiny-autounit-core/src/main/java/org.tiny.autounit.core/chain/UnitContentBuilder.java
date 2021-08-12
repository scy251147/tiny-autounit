package org.tiny.autounit.core.chain;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassContent;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.strategy.UnitBuildFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shichaoyang
 * @Description: 生成测试内容
 * @date 2021-07-30 15:42
 */
@Slf4j
public class UnitContentBuilder {

    public List<UnitClassContent> build(List<UnitClassMethod> unitClassMethodList, String packageName) {

        //参数校验
        if (unitClassMethodList == null || unitClassMethodList.size() <= 0) {
            return null;
        }

        List<UnitClassContent> unitClassBuilds = new ArrayList<>();
        for (UnitClassMethod unitClassMethod : unitClassMethodList) {
            try {
                UnitClassContent unitClassBuild = new UnitClassContent();
                unitClassBuild.setPackageName(unitClassMethod.getCtClass().getPackageName());
                unitClassBuild.setClassName(unitClassMethod.getCtClass().getName());
                unitClassBuild.setContent(UnitBuildFactory.makeContent(unitClassMethod, packageName));
                unitClassBuilds.add(unitClassBuild);
            } catch (Exception e) {
                log.error("UnitContentBuilder.build failed on for loop, will ignore.", e);
            }
        }

        return unitClassBuilds;
    }
}
