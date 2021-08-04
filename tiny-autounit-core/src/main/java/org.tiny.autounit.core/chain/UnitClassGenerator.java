package org.tiny.autounit.core.chain;

import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassContent;
import org.tiny.autounit.core.utils.RegexUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author shichaoyang
 * @Description: 单元测试文件生成器
 * @date 2021-07-28 17:36
 */
@Slf4j
public class UnitClassGenerator {

    public void generate(List<UnitClassContent> unitClassBuilds) {

        //参数校验
        if (unitClassBuilds == null || unitClassBuilds.size() <= 0) {
            return;
        }

        //遍历循环
        for (UnitClassContent unitClassBuild : unitClassBuilds) {
            createTestClass(unitClassBuild.getClassName(), unitClassBuild.getContent());
        }

        //生成基类
        //TODO
    }

    /**
     * 创建类文件
     *
     * @param fileName
     * @param fileContent
     */
    public boolean createTestClass(String fileName, String fileContent) {
        try {
            String fmtFileName = RegexUtil.getFormatedClassName(fileName) + ".java";
            PrintWriter writer = new PrintWriter(fmtFileName, "UTF-8");
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            log.error("UnitClassGenerator.createTestClass error", e);
            return false;
        }
        return true;
    }

}
