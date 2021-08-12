package org.tiny.autounit.core.utils;

import javassist.CtClass;
import org.tiny.autounit.core.model.UnitParamData;
public class MetaDataUtil {

    public static UnitParamData getMetaParamData(String className) {
        UnitParamData unitParamData = null;
        if (className.equals("java.lang.String")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("String");
            unitParamData.setNewName("'testData'");
        }else if (className.equals("int")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("int");
            unitParamData.setNewName("0");
        } else if (className.equals("java.lang.Integer")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Integer");
            unitParamData.setNewName("0");
        } else if (className.equals("long")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("long");
            unitParamData.setNewName("0l");
        }  else if (className.equals("java.lang.Long")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Long");
            unitParamData.setNewName("0l");
        } else if (className.equals("float")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("float");
            unitParamData.setNewName("0.0");
        }  else if (className.equals("java.lang.Float")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Float");
            unitParamData.setNewName("0.0");
        } else if (className.equals("double")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("double");
            unitParamData.setNewName("0.0");
        }  else if (className.equals("java.lang.Double")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Double");
            unitParamData.setNewName("0.0");
        } else if (className.equals("byte")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("byte");
            unitParamData.setNewName("0");
        }  else if (className.equals("java.lang.Byte")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Byte");
            unitParamData.setNewName("0");
        } else if (className.equals("short")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("short");
            unitParamData.setNewName("0");
        }  else if (className.equals("java.lang.Short")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Short");
            unitParamData.setNewName("0");
        } else if (className.equals("boolean")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("boolean");
            unitParamData.setNewName("true");
        }  else if (className.equals("java.lang.Boolean")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Boolean");
            unitParamData.setNewName("true");
        }  else if (className.equals("char")){
            unitParamData = new UnitParamData();
            unitParamData.setClassName("char");
            unitParamData.setNewName("a");
        }  else if (className.equals("java.lang.Character")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Character");
            unitParamData.setNewName("new Character('a')");
        } else if (className.contains("List")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("List");
            unitParamData.setNewName("new ArrayList()");
        }else if (className.contains("Map")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Map");
            unitParamData.setNewName("new HashMap()");
        }
//        else if(className.contains(".")) {
//            String name = className.substring(className.lastIndexOf(".") + 1, className.length());
//            unitParamData = new UnitParamData();
//            unitParamData.setClassName(name);
//            unitParamData.setNewName("new " + name + "()");
//        }
        return unitParamData;
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
        else if (returnType.getName().equals("long") || returnType.getName().equals("java.lang.Long")) {
            return "0l";
        }
        else if (returnType.getName().equals("float") || returnType.getName().equals("java.lang.Float")) {
            return "0.0";
        }
        else if (returnType.getName().equals("double") || returnType.getName().equals("java.lang.Double")) {
            return "0.0";
        }
        else if (returnType.getName().equals("byte") || returnType.getName().equals("java.lang.Byte")) {
            return "0";
        }
        else if (returnType.getName().equals("short") || returnType.getName().equals("java.lang.Short")) {
            return "0";
        }
        else if (returnType.getName().equals("boolean") || returnType.getName().equals("java.lang.Boolean")) {
            return "true";
        }
        else if (returnType.getName().equals("char") || returnType.getName().equals("java.lang.Character")) {
            return "a";
        } else if (returnType.getName().contains(".")) {
            String returnName = returnType.getName();
            String name = returnName.substring(returnName.lastIndexOf(".") + 1, returnName.length());
            return "new " + name + "()";
        }else {
            return "Mockito.any()";
        }
    }



}
