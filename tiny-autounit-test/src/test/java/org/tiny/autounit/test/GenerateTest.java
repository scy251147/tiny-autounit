package org.tiny.autounit.test;

import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.chain.UnitClassGenerator;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-07-30 15:02
 */
public class GenerateTest {

    @Test
    public void classFileGenerateTest(){

        UnitClassGenerator unitClassGenerator = new UnitClassGenerator();
        boolean myfirstTest = unitClassGenerator.createTestClass("myfirstTest", "package com.jd.migration.service.domainservice;\n" +
                "\n" +
                "import com.jd.migration.matrix2.sdk.domain.OrderModel;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "@Service\n" +
                "public class GetUserDiscountServiceImpl implements GetUserDiscountService {\n" +
                "    @Override\n" +
                "    public Double getDiscount(OrderModel order) {\n" +
                "        return null;\n" +
                "    }\n" +
                "}\n");

        assert myfirstTest == true;

    }

}
