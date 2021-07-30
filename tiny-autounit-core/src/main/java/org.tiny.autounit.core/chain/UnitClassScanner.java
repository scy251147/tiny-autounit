package org.tiny.autounit.core.chain;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shichaoyang
 * @Description: 类文件扫描器,扫描特定注解，返回类集合
 * @date 2021-07-28 17:31
 */
@Slf4j
public class UnitClassScanner {

    /**
     * 扫描包下的所有类
     * @param packageName
     * @return
     */
    public Set<Class> scanAnnotations(String packageName) {
        Set<Class> classes = findAllClassesUsingClassLoader(packageName);
        return classes;
    }

    /**
     * 利用类加载器加载指定包名下的所有类
     * @param packageName
     * @return
     */
    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader
                .lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    /**
     * 根据类名及报名，创建类对象
     * @param className
     * @param packageName
     * @return
     */
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            log.error("UnitClassScanner.getClass error", e);
        }
        return null;
    }
}
