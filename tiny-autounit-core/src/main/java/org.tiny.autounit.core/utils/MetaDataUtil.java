package org.tiny.autounit.core.utils;

import javassist.CtClass;
import org.tiny.autounit.core.model.UnitParamData;
public class MetaDataUtil {

    public static UnitParamData getMetaParamData(String className) {
        UnitParamData unitParamData = null;
        if (className.equals("java.lang.String")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("String");
            unitParamData.setNewName("\"testData\"");
        } else if (className.equals("int")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("int");
            unitParamData.setNewName("0");
        } else if (className.equals("java.lang.Integer")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Integer");
            unitParamData.setNewName("0");
        } else if (className.equals("long")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("long");
            unitParamData.setNewName("0l");
        } else if (className.equals("java.lang.Long")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Long");
            unitParamData.setNewName("0l");
        } else if (className.equals("float")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("float");
            unitParamData.setNewName("0.0");
        } else if (className.equals("java.lang.Float")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Float");
            unitParamData.setNewName("0.0");
        } else if (className.equals("double")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("double");
            unitParamData.setNewName("0.0");
        } else if (className.equals("java.lang.Double")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Double");
            unitParamData.setNewName("0.0");
        } else if (className.equals("byte")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("byte");
            unitParamData.setNewName("0");
        } else if (className.equals("java.lang.Byte")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Byte");
            unitParamData.setNewName("0");
        } else if (className.equals("short")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("short");
            unitParamData.setNewName("0");
        } else if (className.equals("java.lang.Short")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Short");
            unitParamData.setNewName("0");
        } else if (className.equals("boolean")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("boolean");
            unitParamData.setNewName("true");
        } else if (className.equals("java.lang.Boolean")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Boolean");
            unitParamData.setNewName("true");
        } else if (className.equals("char")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("char");
            unitParamData.setNewName("a");
        } else if (className.equals("java.lang.Character")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Character");
            unitParamData.setNewName("new Character('a')");
        } else if (className.contains("List")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("List");
            unitParamData.setNewName("new ArrayList()");
        } else if (className.contains("Map")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Map");
            unitParamData.setNewName("new HashMap()");
        } else if (className.contains("Set")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Set");
            unitParamData.setNewName("new HashSet()");
        }else if (className.contains("Collection")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Collection");
            unitParamData.setNewName("new ArrayList()");
        }else {
            String name = className.substring(className.lastIndexOf(".") + 1, className.length());
            unitParamData = new UnitParamData();
            unitParamData.setClassName(name);
            unitParamData.setNewName("new " + name + "()");
            unitParamData.setAnalysisFields(true);
        }
        return unitParamData;
    }
}
