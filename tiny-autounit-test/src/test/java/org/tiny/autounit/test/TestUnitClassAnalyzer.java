package org.tiny.autounit.test;

import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.chain.UnitClassAnalyzer;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.test.biz.UnitBizService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 14:44
 */
public class TestUnitClassAnalyzer {

    @Test
    public void analysisClassTest() {

        Set<Class> classSet = new HashSet<Class>();
        classSet.add(UnitBizService.class);

        UnitClassAnalyzer unitClassAnalyzer = new UnitClassAnalyzer();
        List<UnitClassMethod> analysis = unitClassAnalyzer.analysis(classSet);

        assert analysis != null && analysis.size() == 1 && analysis.get(0).getMethodPairs().size() == 6;
    }

}
