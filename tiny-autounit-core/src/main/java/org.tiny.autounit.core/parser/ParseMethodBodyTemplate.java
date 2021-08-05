package org.tiny.autounit.core.parser;

import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.model.context.UnitMockModel;
import org.tiny.autounit.core.utils.ReflectUtil;
import org.tiny.autounit.core.utils.RegexUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shichaoyang
 * @Description: 方法体解析
 * @date 2021-08-04 18:16
 */
@Slf4j
public class ParseMethodBodyTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        //实例打桩
        builder.append(createStubs(methodPair, unitMockContext));
        //数据Mock
        builder.append(mockDataViaLocal(methodPair, unitMockContext));
        builder.append(RegexUtil.newLine());
        return builder.toString();
    }

    /**
     * 打桩
     *
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    private String createStubs(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        //走查方法体，对@Mock中的实体进行打桩
        Set<String> mockedMethods = new HashSet<>();
        for (UnitMockModel unitMockModel : unitMockContext.getUnitMockModelList()) {
            Set<String> set = findMockedMethods(methodPair.getCtMethod(), new HashSet<>(), 0, unitMockModel);
            mockedMethods.addAll(set);
        }
        for (String mockedMethod : mockedMethods) {
            builder.append(mockedMethod).append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
        }
        builder.append(RegexUtil.newLine());
        return builder.toString();
    }

    /**
     * 遍历method，直至找到能力类
     *
     * @param ctMethod
     * @param deep
     */
    public static Set<String> findMockedMethods(CtMethod ctMethod, Set<String> set, int deep, UnitMockModel unitMockModel) {
        try {
            Integer MAX_DEEP = 10;
            ExprEditor exprEditor = new ExprEditor() {
                public void edit(MethodCall m) {
                    try {
                        String methodCallName = m.getClassName();
                        Class refClass = Class.forName(methodCallName);
                        //找到继承对象
                        if (unitMockModel.getClazz().isAssignableFrom(refClass)) {
                            set.add("when(" + unitMockModel.getClassName() + "." + m.getMethodName() + "(Mockito.any())).thenReturn(Mockito.any());");
                        }
                        //递归查找
                        else {
                            if (deep > MAX_DEEP) {
                                return;
                            }
                            findMockedMethods(m.getMethod(), set, deep + 1, unitMockModel);
                        }
                    } catch (Exception e) {
                        log.error("ParseMethodBodyTemplate.findMockedMethods.loop error. ", e);
                    }
                }
            };
            ctMethod.instrument(exprEditor);
        } catch (Exception e) {
            log.error("ParseMethodBodyTemplate.findMockedMethods error", e);
        }

        return set;
    }

    /**
     * 从本地构造数据
     *
     * @return
     */
    private String mockDataViaLocal(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        builder.append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
        for (Class<?> aClass : methodPair.getMethod().getParameterTypes()) {
            String variableName = unitMockContext.getUnitInjectModel().getClassName();
            builder.append(ReflectUtil.setMockDataByClass(aClass, variableName));
        }
        return builder.toString();
    }

    /**
     * 从远程Mock Server来构造数据
     *
     * @return
     */
    private String mockDataViaRemote() {
        return null;
    }
}