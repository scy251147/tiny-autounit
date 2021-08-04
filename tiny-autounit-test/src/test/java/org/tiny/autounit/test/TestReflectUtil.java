package org.tiny.autounit.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.utils.ReflectUtil;
import org.tiny.autounit.test.biz.PriceEntity;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 20:32
 */
public class TestReflectUtil {

    @Test
    public void testGenerateMockDataByClass() {
        String priceEntity = ReflectUtil.setMockDataByClass(PriceEntity.class, "priceEntity");
        System.out.println(JSON.toJSONString(priceEntity));
        assert priceEntity != null;
    }

}
