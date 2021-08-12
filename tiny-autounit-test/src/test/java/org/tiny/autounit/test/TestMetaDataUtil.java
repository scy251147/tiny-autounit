package org.tiny.autounit.test;

import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.model.UnitParamData;
import org.tiny.autounit.core.utils.MetaDataUtil;
import org.tiny.autounit.test.biz.PriceEntity;

import java.util.ArrayList;

public class TestMetaDataUtil {

    @Test
    public void testString() {
        UnitParamData metaParamData = MetaDataUtil.getMetaParamData(java.lang.String.class.getName());
        System.out.println(metaParamData);
        assert metaParamData != null && metaParamData.getClassName().equals("String");
    }

    @Test
    public void testArrayList() {
        UnitParamData metaParamData = MetaDataUtil.getMetaParamData(ArrayList.class.getName());
        System.out.println(metaParamData);
        assert metaParamData != null && metaParamData.getClassName().equals("List");
    }

    @Test
    public void testObject(){
        UnitParamData metaParamData = MetaDataUtil.getMetaParamData(PriceEntity.class.getName());
        System.out.println(metaParamData);
        assert metaParamData == null;
    }

}
