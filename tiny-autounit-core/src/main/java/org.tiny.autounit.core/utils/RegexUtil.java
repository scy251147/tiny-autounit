package org.tiny.autounit.core.utils;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 11:13
 */
public class RegexUtil {

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassName(String classFullName) {
        return getClassName(classFullName, ".");
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassVariableName(String classFullName) {
        String className = getClassName(classFullName);
        return new String(className.charAt(0) + "").toLowerCase() + className.substring(1, className.length());
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getFormatedClassName(String classFullName) {
        return "Test" + getClassName(classFullName);
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassName(String classFullName, String splitter) {
        return classFullName.substring(classFullName.lastIndexOf(splitter) + 1, classFullName.length());
    }

    /**
     * 根据类全路径名称获取路径名
     *
     * @param classFullName
     */
    public static String getClassPath(String classFullName, String splitter) {
        return classFullName.substring(1, classFullName.lastIndexOf(splitter));
    }

    /**
     * 根据类全路径名称获取路径名
     *
     * @param classFullName
     */
    public static String getClassFullPath(String classFullName) {
        return classFullName.substring(1, classFullName.length());
    }
    /**
     * 新行
     * @return
     */
    public static String newLine(){
        return "\n";
    }

    /**
     * 缩进4
     * @return
     */
    public static String new4Tab(){
        return "    ";
    }

    /**
     * 缩进2
     * @return
     */
    public static String new3Tab(){
        return "   ";
    }

}
