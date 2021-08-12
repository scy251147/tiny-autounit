package org.tiny.autounit.test;

import com.alibaba.fastjson.JSON;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.UnitBootStrap;
import org.tiny.autounit.core.utils.MetaDataUtil;
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

    @Test
    public void testSetReturnDataByReturnType() throws NotFoundException {
        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(new ClassClassPath(TestReflectUtil.class));
        CtClass ctClass = pool.getCtClass("java.lang.Integer");
        String s = MetaDataUtil.setReturnDataByReturnType(ctClass);
        assert s!=null && s.equals("0");
    }

    @Test
    public void testSetReturnDataByReturnTypeWithObject() throws NotFoundException {
        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(new ClassClassPath(TestReflectUtil.class));
        CtClass ctClass = pool.getCtClass("org.tiny.autounit.test.biz.OrderModel");
        String s = MetaDataUtil.setReturnDataByReturnType(ctClass);
        System.out.println(s);
        assert s!=null ;
    }

}
