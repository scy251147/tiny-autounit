package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.model.UnitMethodPair;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.utils.ReflectUtil;
import org.tiny.autounit.core.utils.RegexUtil;

/**
 * @author shichaoyang
 * @Description: 方法体解析
 * @date 2021-08-04 18:16
 */
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
        //builder.append("//todo 构造数据并打桩" + methodPair.getMethod().getParameterCount());
        //走查方法体，对@Mock中的实体进行打桩
        
        builder.append(RegexUtil.newLine());
        return builder.toString();
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