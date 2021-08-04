package org.tiny.autounit.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.chain.UnitClassAnalyzer;
import org.tiny.autounit.core.chain.UnitClassScanner;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.strategy.UnitFieldHandleStrategy;
import java.util.List;
import java.util.Set;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-07-30 17:11
 */
public class TestUnitFieldHandleStrategy {

    @Test
    public void injectFieldTest() {

        UnitClassScanner unitClassScanner = new UnitClassScanner();
        Set<Class> allClassesUsingClassLoader = unitClassScanner.findAllClassesUsingClassLoader("org.tiny.autounit.test.biz");
        assert allClassesUsingClassLoader != null && allClassesUsingClassLoader.size() > 0;

        UnitClassAnalyzer unitClassAnalyzer = new UnitClassAnalyzer();
        List<UnitClassMethod> analysis = unitClassAnalyzer.analysis(allClassesUsingClassLoader);
        assert analysis != null && analysis.size() > 0;

        for (UnitClassMethod unitClassMethod : analysis) {
            UnitFieldHandleStrategy unitFieldHandleStrategy = new UnitFieldHandleStrategy();
            UnitStrategyContent build = unitFieldHandleStrategy.build(unitClassMethod, null);
            System.out.println(JSON.toJSONString(build));
            assert build != null && build.getContent().containsKey(UnitClassType.inject_field);
        }

    }

}
