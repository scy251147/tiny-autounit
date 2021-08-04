package org.tiny.autounit.core.chain;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.UnitBootStrap;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.sdk.UnitExeclude;
import org.tiny.autounit.sdk.UnitWalk;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author shichaoyang
 * @Description: 类文件解析器，解析类文件
 * @date 2021-07-28 17:35
 */
@Slf4j
public class UnitClassAnalyzer {

    /**
     * 根据java文件，获取需要生成测试用例的类
     *
     * @param classes
     */
    public List<UnitClassMethod> analysis(Set<Class> classes) {

        //得到当前对象池
        ClassPool pool = new ClassPool(true);

        //插入当前类加载器
        pool.insertClassPath(new ClassClassPath(UnitBootStrap.class));

        List<UnitClassMethod> unitClassMethodList = new ArrayList<>();

        for (Class aClass : classes) {
            try {
                CtClass ctClass = pool.get(aClass.getName());
                Object unitWalk = ctClass.getAnnotation(UnitWalk.class);
                if (unitWalk != null) {

                    //保存类对象
                    UnitClassMethod unitClassMethod = new UnitClassMethod();
                    unitClassMethod.setCtClass(ctClass);

                    //保存方法列表
                    CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                    for (CtMethod declaredMethod : declaredMethods) {
                        Object unitExeclude = declaredMethod.getAnnotation(UnitExeclude.class);
                        if (unitExeclude == null) {
                            UnitMethodPair unitMethodPair = new UnitMethodPair();
                            unitMethodPair.setCtMethod(declaredMethod);
                            for (Method method : aClass.getDeclaredMethods()) {
                                //如果是同一个方法，则暂存
                                if (method.getName().equals(declaredMethod.getName())) {
                                    unitMethodPair.setMethod(method);
                                }
                            }
                            unitClassMethod.getMethodPairs().add(unitMethodPair);
                        }
                    }
                    unitClassMethodList.add(unitClassMethod);
                }
            } catch (Exception e) {
                log.error("UnitClassAnalyzer.analysis get class error", e);
            }

        }
        return unitClassMethodList;
    }
}
