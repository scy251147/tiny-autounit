package org.tiny.autounit.core;

import org.tiny.autounit.core.chain.UnitClassAnalyzer;
import org.tiny.autounit.core.chain.UnitClassGenerator;
import org.tiny.autounit.core.chain.UnitClassScanner;
import org.tiny.autounit.core.chain.UnitContentBuilder;
import org.tiny.autounit.core.model.UnitClassContent;
import org.tiny.autounit.core.model.UnitClassMethod;
import java.util.List;
import java.util.Set;

/**
 * @author shichaoyang
 * @Description: 启动类
 * @date 2021-07-26 16:58
 */
public class UnitBootStrap {

    /**
     * 可以在main中调用此类以便于生成测试用例
     */
    public static void start(String packageName) {

        //1. 扫描整个项目，获取注解
        UnitClassScanner scanner = new UnitClassScanner();
        Set<Class> classes = scanner.scanAnnotations(packageName);

        //2. 循环带有注解的类集合,找到需要生成的类方法集合
        UnitClassAnalyzer analyzer = new UnitClassAnalyzer();
        List<UnitClassMethod> unitClassMethods = analyzer.analysis(classes);

        //3. 生成单测内容
        UnitContentBuilder builder = new UnitContentBuilder();
        List<UnitClassContent> unitClassBuilds = builder.build(unitClassMethods, packageName);

        //4. 生成单测类并保存
        UnitClassGenerator generator = new UnitClassGenerator();
        generator.generate(unitClassBuilds);

    }

}
