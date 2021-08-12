package org.tiny.autounit.core.utils;

import javassist.CtClass;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author shichaoyang
 * @Description: 反射辅助类
 * @date 2021-08-04 20:23
 */
public class ReflectUtil {

    /**
     * 生成mock数据
     *
     * @param clazz
     * @return
     */
    public static String setMockDataByClass(Class clazz, String classVariableName) {
        StringBuilder builder = new StringBuilder();
        String className = RegexUtil.getClassName(clazz.getName());
        builder.append(className + " " + classVariableName + " = new " + className + "();")
                .append(RegexUtil.newLine());
        for (Field field : clazz.getDeclaredFields()) {
            builder.append(RegexUtil.new4Tab())
                    .append(RegexUtil.new3Tab())
                    .append(classVariableName + formatSetField(field))
                    .append(RegexUtil.newLine());
        }
        return builder.toString();
    }

    /**
     * 根据返回类型生成相应的返回值
     * @param returnType
     * @return
     */
    public static String setReturnDataByReturnType(CtClass returnType) {
        if (returnType.getName().equals("int") || returnType.getName().equals("java.lang.Integer")) {
            return "0";
        }
        if (returnType.getName().equals("long") || returnType.getName().equals("java.lang.Long")) {
            return "0l";
        }
        if (returnType.getName().equals("float") || returnType.getName().equals("java.lang.Float")) {
            return "0.0";
        }
        if (returnType.getName().equals("double") || returnType.getName().equals("java.lang.Double")) {
            return "0.0";
        }
        if (returnType.getName().equals("byte") || returnType.getName().equals("java.lang.Byte")) {
            return "0";
        }
        if (returnType.getName().equals("short") || returnType.getName().equals("java.lang.Short")) {
            return "0";
        }
        if (returnType.getName().equals("boolean") || returnType.getName().equals("java.lang.Boolean")) {
            return "true";
        }
        if (returnType.getName().equals("char") || returnType.getName().equals("java.lang.Character")) {
            return "a";
        }

        if (returnType.getName().contains(".")) {
            String returnName = returnType.getName();
            String name = returnName.substring(returnName.lastIndexOf("."), returnName.length());
            return "new " + name + "()";
        }

        return "Mockito.any()";
    }

    /**
     * 类似输出 .setName("test");
     *
     * @param field
     * @return
     */
    private static String formatSetField(Field field) {
        if (field.getType().isAssignableFrom(String.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(\"testData\");";
        }
        if (field.getType().isAssignableFrom(BigDecimal.class) || field.getType().isAssignableFrom(Double.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(0d);";
        }
        if (field.getType().isAssignableFrom(Long.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(0l);";
        }
        if (field.getType().isAssignableFrom(Integer.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(0);";
        }
        if (field.getType().isAssignableFrom(Float.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(0f);";
        }

        return ".set"
                + (field.getName().charAt(0) + "").toUpperCase()
                + field.getName().substring(1, field.getName().length())
                + "(null);";
    }



}
