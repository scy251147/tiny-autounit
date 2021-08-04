package org.tiny.autounit.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author shichaoyang
 * @Description: 文件操作辅助类
 * @date 2021-08-03 21:16
 */
@Slf4j
public class FileOpsUtil {

    /**
     * 读取模板配置
     * @param templatePath
     * @return
     */
    public static String readTemplate(String templatePath) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(templatePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (String line; (line = reader.readLine()) != null; ) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("UnitBuildFactory.readTemplate error", e);
        }
        return stringBuilder.toString();
    }

}
