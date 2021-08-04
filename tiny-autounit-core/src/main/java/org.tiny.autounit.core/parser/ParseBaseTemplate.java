package org.tiny.autounit.core.parser;

import org.tiny.autounit.core.utils.FileOpsUtil;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 9:40
 */
public class ParseBaseTemplate implements ITemplateParse {

    @Override
    public String parse(String packageName) {
        String baseTemplate = FileOpsUtil.readTemplate("template/BaseTemplate");
        return baseTemplate.replace("$${template-package-name}$$", packageName);
    }
}
