package org.tiny.autounit.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.chain.UnitClassAnalyzer;
import org.tiny.autounit.core.chain.UnitClassScanner;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.strategy.UnitMethodHandleStrategy;

import java.util.List;
import java.util.Set;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 13:53
 */
public class TestUnitMethodHandleStrategy {

    @Test
    public void injectMethodTest() {

        UnitClassScanner unitClassScanner = new UnitClassScanner();
        Set<Class> allClassesUsingClassLoader = unitClassScanner.findAllClassesUsingClassLoader("org.tiny.autounit.test.biz");
        assert allClassesUsingClassLoader != null && allClassesUsingClassLoader.size() > 0;

        UnitClassAnalyzer unitClassAnalyzer = new UnitClassAnalyzer();
        List<UnitClassMethod> analysis = unitClassAnalyzer.analysis(allClassesUsingClassLoader);
        assert analysis != null && analysis.size() > 0;

        for (UnitClassMethod unitClassMethod : analysis) {
            UnitMethodHandleStrategy methodHandleStrategy = new UnitMethodHandleStrategy();
            UnitStrategyContent build = methodHandleStrategy.build(unitClassMethod);
            System.out.println(JSON.toJSONString(build));
            assert build != null && build.getContent().containsKey(UnitClassType.test_method_name);
        }

    }

}
